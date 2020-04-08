/*function enviar(){
	var departamento=document.getElementById("Sdepartamento").value;
	var provincia = $("#provincia").val();
	var distrito = $("#distrito").val();
	var tipoDoc = $("#tipDoc").val();
	var mes = $("#mes").val();
	var check_error = $("#check_error").val();
	
	console.log(departamento);
	console.log(provincia);
	console.log(distrito);
	console.log(tipoDoc);
	console.log(mes);
	console.log(check_error);
	
	var jsonEnvio={
			departamento:departamento,
			provincia:provincia,
			distrito:distrito,
			tipoDoc:tipoDoc,
			mes:mes,
			check_error:check_error
	}
	
	$.ajax({
		contentType: "application/json",
		url:"/atenciones/listarDatos",
		datatype:"application/json",
		type:'POST',
		data:JSON.stringify(jsonEnvio),
		dataType:'json',
		success:function(data){
			console.log(data);
		},
		error:function(){
			console.log("error");
		}
	})
	
	
};*/




function enviar(){
	var departamento=document.getElementById("Sdepartamento").value;
	var provincia = $("#provincia").val();
	var distrito = $("#distrito").val();
	var tipoDoc = $("#tipDoc").val();
	var mes = $("#mes").val();
	var check_error = $("#check_error").val();
	var anio=$("#anio").val();
	
	/*console.log("departamento"+departamento);
	console.log("provincia"+provincia);
	console.log("distrito"+distrito);
	console.log("tipoDoc"+tipoDoc);
	console.log("mes"+mes);
	console.log("check_error"+check_error);*/
	
	 departamento= departamento.replace(/ /g, "_");
	 provincia = provincia.replace(/ /g, "_");
	 distrito = distrito.replace(/ /g, "_");
	 if(tipoDoc!=null){
	 tipoDoc = tipoDoc.replace(/ /g, "_");}
	 anio = anio.replace(/ /g, "_");
	 mes = mes.replace(/ /g, "_");
	
	//---------------------------------------------------------------
	if(check_error=="true"){
		 
		 if(departamento=="Escoja_una_opcion" || provincia=="Escoja_una_opcion" || tipoDoc===null){
			 
			 if(departamento=="Escoja_una_opcion" ){
				 var a="Complete el campo DEPARTAMENTO por favor" ;
			 }
			 
			 else if(provincia=="Escoja_una_opcion" ){
				 var a="Complete el campo PROVINCIA por favor" ;
			 }
			 
			 else if(tipoDoc===null){
				 var a="Complete el campo TIPO DE DOCUMENTO por favor" ;
			 }
			 
			 $('#messages').css('display', 'block');
			 $("#texto").text(a);
			 setTimeout(function() { $("#messages").css('display', 'none'); }, 3000);
			  
			 return 0;
		 }
		 
	}
	
	else{
		 
		 if(departamento=="Escoja_una_opcion" || provincia=="Escoja_una_opcion" || distrito=="Escoja_una_opcion" || tipoDoc===null || anio=="Escoja_una_opcion" ||mes=="Escoja_una_opcion"){
			 
			 if(departamento=="Escoja_una_opcion" ){
				 var a="Complete el campo DEPARTAMENTO por favor" ;
			 }
			 
			 else if(provincia=="Escoja_una_opcion" ){
				 var a="Complete el campo PROVINCIA por favor" ;
			 }
			 
			 else if(distrito=="Escoja_una_opcion"){
				 var a="Complete el campo DISTRITO por favor" ;
			 }
			 
			 else if(tipoDoc===null){
				 var a="Complete el campo TIPO DE DOCUMENTO por favor" ;
			 }
			 
			 else if(anio=="Escoja_una_opcion" ){
				 var a="Complete el campo AÑO por favor" ;
			 }
			 
			 else{
				 var a="Complete el campo MES por favor" ;
			 }
			 
			 $('#messages').css('display', 'block');
			 $("#texto").text(a);
			 setTimeout(function() { $("#messages").css('display', 'none'); }, 3000);
			  
			 return 0;
		 } 
	
	}
	
	
	var url='/atenciones/listarDatos';
	url=url+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
	+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&page='
	/*
	var pdf="false";
	var urlPDF=url+'&'+'pdf='+pdf;
	console.log(url);
	console.log(urlPDF);*/
	
	
	
	$('#mensajeResultado').css('display', 'block');
	//setTimeout(function() { $("#mensajeResultado").css('display', 'none'); }, 5000);
	//var token = $("input[name='_csrf']").val();
	//var header = "X-CSRF-TOKEN";
	
	$("#resultados2").load(url+'0',function(){
		$("#mensajeResultado").css('display', 'none'); 
		var documentoDownload=document.getElementById("excel");
		var urlExcel='/atenciones/exportarDatos';
		urlExcel=urlExcel+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
		+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&'+'documento=excel'
		documentoDownload.href=urlExcel;
		console.log(documentoDownload.href);
		var documentoprueba=document.getElementById("excel");
		console.log(documentoprueba.href);
		
		var documentoDownloadPDF=document.getElementById("pdf");
		var urlPDF='/atenciones/exportarDatos';
		urlPDF=urlPDF+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
		+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&'+'documento=pdf'
		documentoDownloadPDF.href=urlPDF;
		console.log(documentoDownloadPDF.href);
		var documentoprueba=document.getElementById("pdf");
		console.log(documentoprueba.href);	
	})
	
	/*$.ajax({
		url: '/atenciones/listarPaginador'+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
		+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error,
		beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token)
          },
		type:'GET',
		data:'',
		success:function(data){    
			console.log("bien");
			console.log(data);
			//console.log(data.content)
			//console.log(data.content[0])
			//console.log(data.content[0].idCita) 
			//console.log(data.content.length)*/
			/*$("#resultados2").load(url+'0',function(){
				var documentoDownload=document.getElementById("excel");
				var urlExcel='/atenciones/exportarDatos';
				urlExcel=urlExcel+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
				+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&'+'documento=excel'
				documentoDownload.href=urlExcel;
				console.log(documentoDownload.href);
				var documentoprueba=document.getElementById("excel");
				console.log(documentoprueba.href);
				
				var documentoDownloadPDF=document.getElementById("pdf");
				var urlPDF='/atenciones/exportarDatos';
				urlPDF=urlPDF+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
				+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&'+'documento=pdf'
				documentoDownloadPDF.href=urlPDF;
				console.log(documentoDownloadPDF.href);
				var documentoprueba=document.getElementById("pdf");
				console.log(documentoprueba.href);	
			})*/
			/*for(i=0;i<data.content.length;i++){
				$("#resultados2").append(
					'<tr>'+
					'<td>'+data.content[i].idCita+'</td>'+
					'</tr>'
				);
			}*/
			
				
	
			/*$("#paginadorAtenciones").append(
				'<li class="page-item"><a href="#"</a>Primera</li>'
			)
			for(i=0;i<data.totalPages;i++){
				$("#paginadorAtenciones").append(
						'<li class="page-item"><a href="#" onclick="enviar_paginador('+"'"+url+i+"'"+')" >'+i+'</a></li>'
						
				);
			}
			$("#paginadorAtenciones").append(
				'<li class="page-item"><a href="#" </a>Ultima</li>'
			)
			//console.log(data.totalPages)

			
		},
		error:function(){
			console.log("error");
		}
	})*/


	
	//$("#resultados").load(url,function(){

		//$('#datatable-buttons').DataTable().destroy();		
		/*$('#datatable-buttons2').DataTable( {

	        "language": {
	        	"url": "/idioma/español.json"
	        },
	        dom: 'Bfrtip',
	        buttons: [
	            'print'
	        ]
		});*/
		/*
		var documentoDownload=document.getElementById("excel");
		var urlExcel='/atenciones/exportarDatos';
		urlExcel=urlExcel+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
		+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&'+'documento=excel'
		documentoDownload.href=urlExcel;
		console.log(documentoDownload.href);
		var documentoprueba=document.getElementById("excel");
		console.log(documentoprueba.href);
		
		var documentoDownloadPDF=document.getElementById("pdf");
		var urlPDF='/atenciones/exportarDatos';
		urlPDF=urlPDF+'?'+'departamento='+departamento+'&'+'provincia='+provincia+'&'+'distrito='+distrito
		+'&'+'tipoDoc='+tipoDoc+'&'+'anio='+anio+'&'+'mes='+mes+'&'+'check_error='+check_error+'&'+'documento=pdf'
		documentoDownloadPDF.href=urlPDF;
		console.log(documentoDownloadPDF.href);
		var documentoprueba=document.getElementById("pdf");
		console.log(documentoprueba.href);	
		
	});*/

	
};

function check(){
	var check_error = document.getElementById("check_error");
	 if (check_error.checked == true) {
		 var check_error = $("#check_error").val(true);
         alert("Encontrar errores Activado");
         $("#anio").attr('disabled', 'disabled');
         $("#mes").attr('disabled', 'disabled');
     } else {
    	 var check_error = $("#check_error").val(false);
         alert("Encontrar errores Desactivado");
         $("#anio").removeAttr('disabled');
         $("#mes").removeAttr('disabled');
     }
};

function errores(){
	var tipoDoc = $("#tipDoc").val();
	if(tipoDoc=="S/ DOCUMENTO" || tipoDoc=="Escoja_una_opcion" ||  tipoDoc=="Escoja una opcion"){
		 $('#error').css('visibility', 'hidden');
		 var check_error = $("#check_error").val();
		 console.log(check_error);
		 $('#check_error').prop('checked', false);
		 $('#check_error').val(false);
		 $("#anio").removeAttr('disabled');
         $("#mes").removeAttr('disabled');
         //alert("Encontrar errores Desactivado");
	}
	else{
		 $('#error').css('visibility', 'visible');
	}
};

function enviar_paginador(url){
	$("#resultados2").load(url,function(){})
}


/*function url(){
	var urlExcelDownload=document.getElementById("excel");
	urlExcelDownload=urlExcelDownload+'&'+'documento=excel'
	urlExcelDownload.href=urlExcelDownload;
	console.log(urlExcelDownload.href);
}*/


