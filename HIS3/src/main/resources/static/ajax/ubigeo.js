//$(document).ready(function(){
	var token = $("input[name='_csrf']").val();
	var header = "X-CSRF-TOKEN";
	function departamento(){
		var departamento=document.getElementById("Sdepartamento").value;
		//console.log(departamento);
		var jsonDepartamento={
				nombre:departamento
		}
			$.ajax({
				contentType: "application/json",
				url:"/ubigeo/cargaProvincia",
				beforeSend: function(xhr) {
		            xhr.setRequestHeader(header, token)
		          },
				datatype:"application/json",
				type:'POST',
				data:JSON.stringify(jsonDepartamento),
				dataType:'json',
				success:function(data){
					//console.log(data);
					var select = document.getElementsByName("provincia")[0];
					$("#provincia").empty();
					var option=document.createElement("option");
					option.text = "Escoja una opcion";
					select.add(option);
					for (value in data){
						option=document.createElement("option");
						option.text = data[value];
						select.add(option);
					}	
				},
				error:function(){
					console.log("error");
				}
			})
		
	};
	
	$("#provincia").change(function(){
		var departamento=document.getElementById("Sdepartamento").value;
		var provincia = $(this).val();
		console.log(provincia);
		var jsonProvincia={
				departamento:departamento,
				provincia:provincia
		};
		$.ajax({
			contentType: "application/json",
			url:"/ubigeo/cargaDistrito",
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(header, token)
	          },
			datatype:"application/json",
			type:'POST',
			data:JSON.stringify(jsonProvincia),
			dataType:'json',
			success:function(data){
				console.log(data);
				var select = document.getElementsByName("distrito")[0];
				$("#distrito").empty();
				var option=document.createElement("option");
				option.text = "Escoja una opcion";
				select.add(option);
				for (value in data){
					option=document.createElement("option");
					option.text = data[value];
					select.add(option);
				}
			},
			error:function(){
				console.log("error");
			}
		})		
	});
	
	$("#distrito").change(function(){
		var distrito = $(this).val();
		console.log(distrito);
	})
	
//});