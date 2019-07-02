/**
 * This module includes some utility functions
 */

var setUrl = function(domain){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + domain;
}

module.exports = {
	setUrl: setUrl
}