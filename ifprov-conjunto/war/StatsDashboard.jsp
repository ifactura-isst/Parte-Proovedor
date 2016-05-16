<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Estadisticas - iFactura - Proveedor</title>

		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/main.css" />
		
		
</head>
<body class="landing">
		<div id="page-wrapper">

			<!-- Header -->
				<header id="header">
					<h1 id="logo"><a href="index.html"><img src="images/logo.png"/></a></h1>
					<nav id="nav">
						<ul>
							<li><c:if test="${user != null}"><c:out value="${user.nickname}"/></c:if></li>
							<li><a class="button special" href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></li>
						</ul>
					</nav>
				</header>

			<!-- Banner -->
				<section id="banner">
					<div class="content menu-ancho text-center">
											
						<div class="col-md-3 height">
							<div id="GraficoGoogleChart-4" style="width: 300px; height: 300px"></div>
						</div>
						<div class="col-md-3 height">
							<div id="GraficoGoogleChart-2" style="width: 300px; height: 300px"></div>
						</div>
						<div class="col-md-3 height">
							<div id="GraficoGoogleChart-1" style="width: 300px; height: 300px"></div>
						</div>	
						<div class="col-md-3 height">
							<div id="GraficoGoogleChart-3" style="width: 300px; height: 300px"></div>
						</div>
											
					</div>
										
				</section>

		</div>

		<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrolly.min.js"></script>
			<script src="js/jquery.dropotron.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="js/main.js"></script>
			<script src="js/bootstrap.min.js"></script>
			<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
			<script src="js/bootstrap.min.js"></script>

			<script type="text/javascript" src="https://www.google.com/jsapi"></script>
			<script>
			   google.load("visualization", "1", {packages:["corechart"]});
			   google.setOnLoadCallback(dibujarGrafico);
			   function dibujarGrafico() {
			     
				 //--Grafico 2--
			     // Tabla de datos: valores y etiquetas de la gráfica
			     var data2 = google.visualization.arrayToDataTable([
			       ['Tipo','Consumo por Mes'],
			       ['Enero', ${Enero}],
			       ['Febrero', ${Febrero}],
			       ['Marzo', ${Marzo}],
			       ['Abril', ${Abril}],
			       ['Mayo', ${Mayo}],
			       ['Junio', ${Junio}],
			       ['Julio', ${Julio}],
			       ['Agosto', ${Agosto}],
			       ['Septiembre', ${Septiembre}],
			       ['Octubre', ${Octubre}],
			       ['Noviembre', ${Noviembre}],
			       ['Dieciembre', ${Diciembre}]
			         
			     ]);
			     var options2 = {
			       title: 'Consumo por Meses',
		           legend: 'none',
		           vAxis: { gridlines: { count: 10 } }
			       //is3D: true
			     }
			          
				 //--Grafico 4-- Porcentaje de reservas hoy
			     // Tabla de datos: valores y etiquetas de la gráfica
			     var data4 = google.visualization.arrayToDataTable([
			       ['Tipo','Nº de facturas'],
			       ['Orange', ${Orange}],
			       ['Movistar', ${Movistar}],
			       ['Vodafone', ${Vodafone}],
			       ['Yoigo', ${Yoigo}] 
			     ]);
			     var options4 = {
			       title: 'Porcentaje de empresas de facturas',
		           legend: 'none',
		           pieHole: 0.5
		           //vAxis: { gridlines: { count: 10 } }
			       //is3D: true
			     }
			   	 //--Grafico 1-- Porcentaje de reservas hoy
			     // Tabla de datos: valores y etiquetas de la gráfica
			     var data1 = google.visualization.arrayToDataTable([
			       ['Usuarios','Numero'],
			       ['Facturas', ${numfacturas}],
			       ['Usuarios', ${numusuarios}]
			     ]);
			     var options1 = {
			       title: 'Usuarios registrados en el sistema',
		           legend: 'none',
		           pieHole: 0.5
		           //vAxis: { gridlines: { count: 10 } }
			       //is3D: true
			     }
			     //--Grafico 3-- Porcentaje de reservas hoy
			     // Tabla de datos: valores y etiquetas de la gráfica
			     var data3 = google.visualization.arrayToDataTable([
			       ['Estado','Nº Puestos'],
			       ['Facturas', ${numfacturas}],
			       ['Usuarios', ${numusuarios}]  
			     ]);
			     var options3 = {
			       title: 'Facturas y usuarios',
		           legend: 'none',
		           pieHole: 0.5
		           //vAxis: { gridlines: { count: 10 } }
			       //is3D: true
			     }
			     // Dibujar el gráfico
			     new google.visualization.ColumnChart( 
			   	     //ColumnChart sería el tipo de gráfico a dibujar
			   	       document.getElementById('GraficoGoogleChart-2')
			   	     ).draw(data2, options2);
			     new google.visualization.PieChart( 
		    	     //ColumnChart sería el tipo de gráfico a dibujar
		    	       document.getElementById('GraficoGoogleChart-4')
		    	     ).draw(data4, options4);
			     // Dibujar el gráfico
			     new google.visualization.ColumnChart( 
			   	     //ColumnChart sería el tipo de gráfico a dibujar
			   	       document.getElementById('GraficoGoogleChart-1')
			   	     ).draw(data1, options1);
			     new google.visualization.PieChart( 
		    	     //ColumnChart sería el tipo de gráfico a dibujar
		    	       document.getElementById('GraficoGoogleChart-3')
		    	     ).draw(data3, options3); 
			   }
			 </script>

	</body>
</html>