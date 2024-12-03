import os

def openFile(fileName):
    global attributes
    global types

    types = []
    attributes = []
    f = open(fileName, 'r');
    lines = f.readlines()
    for lineRaw in lines:
        line = lineRaw.strip()
        t = line.split()[0]
        attr = line.split()[1][:-1]
        types.append(t)
        attributes.append(attr)
#end openFile

def toDocument():
    output = []
    output.append("public Document toDocument(){")
    output.append("\tint i;")
    output.append("\tDocument doc = new Document();")
    for i in range(len(types)):
        attr = attributes[i]
        t = types[i]
        if (t == "int" or t == "String" or t == "List<Integer>" or t == "List<String>"):
            output.append("\tdoc.append(\"" + attr + "\"," + attr + ");");
        elif (t[:5] == "List<"):
            nestedT = t[5:-1]
            listDoc = attr + "DocList"
            output.append("\tList<Document> " + listDoc + " = new ArrayList<>();")
            output.append("\tfor (i=0;i<" + attr + ".size();i++){")
            output.append("\t\t" + listDoc + ".add(" + attr + ".get(i).toDocument());")
            output.append("\t}")
        else:
             output.append("\tdoc.append(\"" + attr + "\"," + attr + ".toDocument());");
            
            
    output.append("\treturn doc;")
    output.append("}")

    for o in output:
        print(o)
#end toDocument

def setFromDoc():
    output = []
    output.append("public void setFromDoc(Document doc){")    
    output.append("\tint i;")
    for i in range(len(types)):
        attr = attributes[i]
        t = types[i]
        if (t == "int"):
            output.append("\t" + attr + " = doc.getInteger(\"" + attr + "\",0);");
        elif (t == "String"):
            output.append("\t" + attr + " = doc.getString(\"" + attr + "\");");
        elif (t == "List<String>" or t == "List<Integer>"):
            output.append("\t" + attr + " = (" + t + ")doc.get(\"" + attr + "\");");
        elif (t[:5] == "List<"):
            nestedT = t[5:-1]
            listDoc = attr + "DocList"
            output.append("\tList<Document> " + listDoc + " = (List<Document>)doc.get(\"" + attr + "\");");
            output.append("\t" + attr + " = new ArrayList<>();");
            output.append("\tfor (i=0;i<" + listDoc + ".size();i++){")
            output.append("\t\t" + nestedT + " e = new " + nestedT + "();" )
            output.append("\t\te.setFromDoc(" + listDoc + ".get(i));")
            output.append("\t\t" + attr + ".add(e);")
            output.append("\t}")
        else:
            output.append("\t" + attr + " = (" + t + ")doc.get(\"" + attr + "\");");
    output.append("}")

    for o in output:
        print(o)
#end toDocument

fileName = 'attrs.txt'
openFile(fileName)
toDocument()
setFromDoc()
