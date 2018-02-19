/**
 * 
 */

function addQuestionChoice(divID){		
	var $parentDiv = $(document.getElementById(divID));
	
	var $newDiv = $parentDiv.children("div:last-child").clone();	
	
	//Find index	
	var lastIndex = $parentDiv.children("div").length - 1;	
		
	var content = lastIndex;
	var re = new RegExp(content,"g");
	var newContent = parseInt(lastIndex+1);		
	
	var replaced = $newDiv.html().replace(re,newContent);
	$newDiv.html(replaced);	
	$newDiv.appendTo($parentDiv);	 
}
function showOrHide(div)
{
	if($("#type").val=="Free Text") {$(div).css("display:none");}
	else {$(div).css("display:block");}
}