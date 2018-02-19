var contextRoot = "/" + window.location.pathname.split( '/' )[1];
function getExistingQuestions(){
	
	
	//var dataToSend = JSON.stringify(serializeObject($('#qnForm')));
	
	$.ajax({
		
		type: 'GET',
		url: contextRoot + '/exam/addExistingQuestion',
		dataType: 'json',           // Accept header
 		//data:dataToSend,
 		contentType: 'application/json',   // Sends - Content-type
		success: function(questionsList) {
			$('#existing').html("");
			var existingHtml="";
			if(questionsList.length>0)
			{			
				 existingHtml+= '<form id="existingForm" action="addExistingExamQuestion" method="POST">';
					$.each(questionsList,function(i,question){
						
					existingHtml+='<input type="radio" value="'+question.questionId+'" name="question.questionId"/>'+question.description+'<br>';
					});
					existingHtml+='<div class="form-group">'+
					'<label for="gradePoint">Grade Point</label>'+
					'<div class="col-sm-6">'+
					'<input type="text" path="gradePoint"/></div></div>'+		
					'<input type=hidden name="questionNumber" value="1"/>'+					
					'<div class="col-lg-offset-2 col-sm-10">'+
					'<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/></div></form>';
					make_hidden('buttons');
			}
			else
				{
				existingHtml='<div style="color:red">No Additional Questions found under this subject!!</div>';
				make_visible("addNew");
				make_hidden("addExisting");
				}
			$('#existing').html(existingHtml);
			make_visible('existing');
			make_hidden('errors');
			
		},

		error: function(errorObject){	
			alert("failure");
			if (errorObject.responseJSON.errorType == "ValidationError") {
				$('#errors').html("");
				$("#errors").append( '<H3 align="center"> Errors found <H3>');             
				
  			    var errorList = errorObject.responseJSON.errors;
 	 	        $.each(errorList,  function(i,error) {			   
 		    		$("#errors").append( error.message + '<br>');
		    	});
 	 	   
 	 	        make_visible('errors');
				make_hidden('existing');
			}
			else {
				alert(errorObject.responseJSON.errors(0));   // "non" Validation Error
				}			
		}
	
	});
}

toggle_visibility = function(id) {
    var element = document.getElementById(id);
    if(element.style.display == 'block')
    	element.style.display = 'none';
    else
    	element.style.display = 'block';
 }	

make_hidden = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'none';
        }	   

make_visible = function(id) {
    var element = document.getElementById(id);
    element.style.display = 'block';
 }	   

resetForm = function(id) {
    var element = document.getElementById(id);
    $(element)[0].reset();

    }	  

// Translate form to array
// Then put in JSON format
 function serializeObject (form)
{
    var jsonObject = {};
    var array = form.serializeArray();
    $.each(array, function() {
         	jsonObject[this.name] = this.value;
    });
    return jsonObject;

};

