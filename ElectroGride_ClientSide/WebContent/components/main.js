$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateCustomerForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hideIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "EmployeeApi",
		type : t,
		data : $("#formCustomer").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onEmployeeSaveComplete(response.responseText, status);
		}	
	});
}); 

function onEmployeeSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hideIDSave").val("");
	$("#formCustomer")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hideIDSave").val($(this).closest("tr").find('#hideIDUpdate').val());     
	$("#eName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#eAddress").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#eEmail").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#eDate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#pno").val($(this).closest("tr").find('td:eq(4)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "EmployeeApi",
		type : "DELETE",
		data : "eID=" + $(this).data("eid"),
		dataType : "text",
		complete : function(response, status)
		{
			onEmployeeDeletedComplete(response.responseText, status);
		}
	});
});

function onEmployeeDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateCustomerForm() {  
	// NAME  
	if ($("#eName").val().trim() == "")  {   
		return "Insert Account Number.";  
		
	} 
	
	 // Address 
	if ($("#eAddress").val().trim() == "")  {   
		return "Insert From Date.";  
	} 
	
	
	//Email
	if ($("#eEmail").val().trim() == "")  {   
		return "Insert To Date."; 
		 
	}
	
	//Date
	if ($("#eDate").val().trim() == "")  {   
		return "Insert Current Reading."; 
		 
	}
	if ($("#pno").val().trim() == "")  {   
		return "Insert Status"; 
		 
	}
	 // is numerical value  
	//var tmpMobile = $("#pno").val().trim();  
	//if (!$.isNumeric(tmpMobile))  {   
	///	return "Insert a numerical value for Mobile Number.";  
		
	//}
	 
	
		

	 
	 return true; 
	 
}
