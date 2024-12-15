import os

def openFile(fileName, filename2):
    global attributes
    global types
    global attributes2
    global types2
    global entityName

    types = []
    attributes = []
    types2 = []
    attributes2 = []
    f = open(fileName, 'r');
    lines = f.readlines()
    for lineRaw in lines:
        line = lineRaw.strip()
        t = line.split()[0]
        attr = line.split()[1][:-1]
        types.append(t)
        attributes.append(attr)
    g = open(fileName2, 'r');
    lines = g.readlines()
    v = True
    for lineRaw in lines:
        line = lineRaw.strip()
        if (len(line) == 0):
            continue;
        if (v == True):
            entityName = line
            v = False
            continue;
        t2 = line.split()[0]
        attr2 = line.split()[1][:-1]
        types2.append(t2)
        attributes2.append(attr2)
#end openFile

def toDocument():
    output = []
    output.append("public Document toDocument(){")
    output.append("\tint i;")
    output.append("\tDocument doc = new Document();")
    for i in range(len(types)):
        attr = attributes[i]
        t = types[i]
        if (attr == "players"):
            output.append("\tfor (i=0;i<players.size();i++){");
            output.append("\t\tplayers.get(i).setIndex(i);");
            output.append("\t\tString n = \"player-\" + players.get(i).getName();");
            output.append("\t\tdoc.append(n, players.get(i).toDocument());");
            output.append("\t}");
        elif (attr == "logger"):
            output.append("\tdoc.append(\"logs\",logger.getLogs());");
        elif (t == "boolean" or t == "int" or t == "String" or t == "List<Integer>" or t == "List<String>"):
            output.append("\tdoc.append(\"" + attr + "\"," + attr + ");");
        elif (t[:5] == "List<"):
            nestedT = t[5:-1]
            listDoc = attr + "DocList"
            output.append("\tList<Document> " + listDoc + " = new ArrayList<>();")
            output.append("\tfor (i=0;i<" + attr + ".size();i++){")
            output.append("\t\t" + listDoc + ".add(" + attr + ".get(i).toDocument());")
            output.append("\t}")
            output.append("\tdoc.append(\"" + attr + "\"," + listDoc + ");");
        else:
            output.append("\tdoc.append(\"" + attr + "\"," + attr + ".toDocument());");
    output.append("\treturn doc;")
    output.append("}")

    for o in output:
        print("\t" + o)
#end toDocument

def setFromDoc():
    output = []
    output.append("public void setFromDoc(Document doc){")    
    output.append("\tint i;")
    for i in range(len(types)):
        attr = attributes[i]
        t = types[i]
        if (attr == "players"):
            nestedT = t[5:-1]
            output.append("\tList<String> playerNames = (List<String>) doc.get(\"playerNames\");");
            output.append("\tplayers = new ArrayList<>();")
            output.append("\tfor (i=0;i<playerNames.size();i++){")
            output.append("\t\tString n = \"player-\" + playerNames.get(i);")
            output.append("\t\tDocument dop = (Document) doc.get(n);")
            output.append("\t\t" + nestedT + " p = new " + nestedT + "();")
            if (nestedT == "Player"):
                output.append("\t\tp.setBoard(this);")
            else:
                boardT = nestedT[:-6]
                output.append("\t\tp.set" + boardT + "(this);")
            output.append("\t\tp.setFromDoc(dop);")
            output.append("\t\tp.setIndex(i);")
            output.append("\t\tplayers.add(p);")
            output.append("\t}")
        elif (attr == "logger"):
            output.append("\tList<String> logs = (List<String>) doc.get(\"logs\");");
            output.append("\tif (logs == null) logger = new Logger(); else logger = new Logger(logs);");
        elif (t == "boolean"):
            output.append("\t" + attr + " = doc.getBoolean(\"" + attr + "\",false);");
        elif (t == "int"):
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
        print("\t" + o)
#end toDocument

def cap(s):
    ans = s[:1].title() + s[1:]
    return ans
#end cap

def toEntity():
    output = []
    output.append("public " + entityName + " to" + entityName + "(){")
    output.append("\tint i;")
    output.append("\t" + entityName + " entity = new " + entityName + "();")
    for i in range(len(attributes2)):
        attr = attributes2[i]
        t = types2[i]
        if (t == "int" or t == "String" or t == "List<Integer>" or t == "List<String>"):
            output.append("\t" + "entity.set" + cap(attr) + "(" + attr + ");");
        elif (t[:5] == "List<"):
            nestedT = t[5:-1]
            output.append("\t" + t + " listOf" +  cap(attr) + " = new ArrayList<>();")
            output.append("\tfor (i=0;i<" + attr + ".size();i++){")
            output.append("\t\tlistOf" + cap(attr) + ".add(" + attr + ".get(i).to" + cap(nestedT) + "());")
            output.append("\t}")
            output.append("\t" + "entity.set" + cap(attr) + "(listOf" +  cap(attr) + ");");
        else:
            output.append("\t" + "entity.set" + cap(attr) + "(" + attr + ".to" + t +"());");
    output.append("\treturn entity;")
    output.append("}")
    for o in output:
        print("\t" + o)
#end toEntity

fileName = 'attrs.txt'
fileName2 = 'entity.txt'
openFile(fileName, fileName2)
toDocument()
setFromDoc()
toEntity()
