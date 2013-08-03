<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xmlns:wairole="http://www.w3.org/2005/01/wai-rdf/GUIRoleTaxonomy#" xmlns:waistate="http://www.w3.org/2005/07/aaa">
<head>
    <script type='text/javascript' src='https://www.google.com/jsapi'></script>

    <script type="text/javascript"  src="<c:url value="/res/js/websockets.js"/>"></script>
</head>

<body>
	<h1>${message}</h1>

    <div id='chart_div'></div>

    <script type='text/javascript'>
        google.load('visualization', '1', {packages:['gauge']});
        google.setOnLoadCallback(initChart);
        var options = {
            width: 400, height: 120,
            redFrom: 900, redTo: 1000,
            yellowFrom:750, yellowTo: 900,
            minorTicks: 50,
            max: 1000
        };

        var data,chart;
        function initChart() {
            data = google.visualization.arrayToDataTable([
                ['Label', 'Value'],
                ['Status', 0]
            ]);

            chart = new google.visualization.Gauge(document.getElementById('chart_div'));
            chart.draw(data, options);
        };

        function changeValue(value){
            data.setValue(0,1,value);
            chart.draw(data, options);
        };
    </script>

<div id="output">

</div>

</body>
</html>