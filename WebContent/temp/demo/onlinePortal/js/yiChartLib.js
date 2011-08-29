(function(global){
    // local object
    var localchart = {
        version: "1.00"
    
    };
    
    var onclickMethod = "false";
    var chartType;
    var usersettings = {};
	var tooltip = false;
    // parsing the xml
    function parseXMLDocFromString(parm){
        var xmlDoc;
        if (window.DOMParser) {
            var parser = new DOMParser();
            xmlDoc = parser.parseFromString(parm, "text/xml");
        }
        else // Internet Explorer
        {
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async = "false";
            xmlDoc.loadXML(parm);
        }
        return xmlDoc;
    };
    
    // set options for pie,donut and funnel charts
    function setPieOptions(){
        var options = {
            // each series color .If there are more series than colors, colors
            // will wrap around and start at the beginning again.
        	
            seriesColors: ['#8FA8E0', '#92C2A0', '#6BBDE9', '#B7D2C8', '#93BEEE', '#419EEB', '#5ea650', '#A47D7C', '#B5CA92'],
            title: {
                show: true
            },
            onClick: "false",
            seriesDefaults: {
                rendererOptions: {
                    showDataLabels: true, // displaying series name
                    dataLabelPositionFactor: '0.6',
                    dataLabelNudge: '0',
                    dataLabels: 'label'
                }
            },
            legend: { // legend setting
                show: true,
                placement: 'outside', // placement postion with respect to
										// graph
                location: 'e', // compass direction, nw, n, ne, e, se, s, sw,
								// w.
                marginTop: '15px'
            },
            
            grid: {
                drawGridLines: false, // wether to draw lines across the grid
										// or not.
                gridLineColor: '#cccccc', // *Color of the grid lines.
                background: '#ffffff', // CSS color spec for background color
										// of grid.
                borderColor: 'white', // CSS color spec for border around
										// grid.
                borderWidth: '2.0', // pixel width of border around grid.
                shadow: true, // draw a shadow for grid.
                shadowAngle: 0, // angle of the shadow. Clockwise from x axis.
                shadowOffset: 0, // offset from the line of the shadow.
                shadowWidth: 0, // width of the stroke for the shadow.
                shadowDepth: 0 // Number of strokes to make when drawing
								// shadow.
                // Each stroke offset by shadowOffset from the last.
            },
            
            highlighter: {
                show: true,
                showMarker: false,
                tooltipLocation: 'cursor', // location of tooltip: n, ne, e,
											// se, s, sw, w, nw,cursor
                showTooltip: true, // show a tooltip with data point values.
                tooltipAxes: 'both', // which axis values to display in the
										// tooltip, x, y or both.
                useAxesFormatters: false, // use the same format string and
											// formatters as used in the axes to
                // display values in the tooltip
                tooltipFormatString: '<b>%s</b>%s ' // sprintf format string for
													// the tooltip. only used if
                // useAxesFormatters is false. Will use sprintf formatter with
                // this string, not the axes formatters.
            
            }
        };
        
        return options;
    };
    
    // set options for line,area and bar charts
    function setBarOptions(){
        var options = {
            // each series color .If there are more series than colors, colors
            // will wrap around and start at the beginning again.
            seriesColors: ['#8FA8E0', '#92C2A0', '#6BBDE9', '#B7D2C8', '#93BEEE', '#419EEB', '#5ea650', '#A47D7C', '#B5CA92'],
            stackSeries: false, // if true, will create a stack plot.
            // Currently supported by line,area and bar graphs.
            onClick: "false",
            showMarker: true,
            title: {
                show: true // title of the chart
            },
            axes: {
                xaxis: {
                    label: 'xaxis', // X axis title text
                    renderer: $.jqplot.CategoryAxisRenderer,
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    //numberTicks: undefined,
                    tickOptions: {
                        fontSize: '10pt',
                        fontFamily: 'verdana',
                        angle: '0'
                        //fontWeight: 'normal',
                        //fontStretch: 1
                    },
                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    labelOptions: { // label setting
                        fontSize: '10pt',
                        fontFamily: 'verdana'
                    }
                },
                yaxis: {
                    min: 0,
                    label: 'yaxis', // yaxis title text
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    //numberTicks: undefined,
                    labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                    labelOptions: { // label settings
                        fontSize: '10pt',
                        fontFamily: 'verdana'
                    },
                    tickOptions: {
                        // labelPosition: 'middle',
                        angle: 0
                    
                    }
                }
            },
            
            // default series settings
            seriesDefaults: {
                show: true, // whether to render the series.
                xaxis: 'xaxis', // either 'xaxis' or 'x2axis'.
                yaxis: 'yaxis', // either 'yaxis' or 'y2axis'.
                color: '', // CSS color spec to use for the line. Determined
							// automatically.
                lineWidth: '2.5', // Width of the line in pixels.
                shadow: true, // show shadow or not.
                shadowAngle: 45, // angle (degrees) of the shadow, clockwise
									// from x axis.
                shadowOffset: '1.25', // offset from the line of the shadow.
                shadowDepth: 3, // Number of strokes to make when drawing
								// shadow. Each
                // stroke offset by shadowOffset from the last.
                
                shadowAlpha: 0.1, // Opacity of the shadow.
                showLine: true, // whether to render the line segments or not.
                showMarker: false, // render the data point markers or not.
                fill: false, // fill under the line,
                fillAndStroke: true, // *stroke a line at top of fill area.
                fillColor: undefined, // *custom fill color for filled lines
										// (default is line color).
                fillAlpha: undefined, // *custom alpha to apply to fillColor.
                markerOptions: {
                    show: true, // wether to show data point markers.
                    style: 'filledCircle', // circle, diamond, square,
											// filledCircle.
                    // filledDiamond or filledSquare.
                    lineWidth: 2, // width of the stroke drawing the marker.
                    size: 9, // size (diameter, edge length, etc.) of the
								// marker.
                    shadow: true, // wether to draw shadow on marker or not.
                    shadowAngle: 45, // angle of the shadow. Clockwise from x
										// axis.
                    shadowOffset: 1, // offset from the line of the shadow,
                    shadowDepth: 3, // Number of strokes to make when drawing
									// shadow. Each stroke
                    // offset by shadowOffset from the last.
                    shadowAlpha: '0.07' // Opacity of the shadow
                }
            },
            
            // Each series has same options as seriesDefaults, You can override
			// each series individually here
            series: [{
                markerOptions: {
                    style: 'x'
                }
            }],
            
            legend: {
                show: true,
                location: 'e', // compass direction, nw, n, ne, e, se, s, sw,
								// w.
                placement: 'outside',
                xoffset: 12, // pixel offset of the legend box from the x (or
								// x2) axis
                yoffset: 12, // pixel offset of the legend box from the y (or
								// y2) axis
                background: '#ffffff'
            },
            grid: {
                drawGridLines: false, // wether to draw lines across the grid
										// or not.
                gridLineColor: '#F4F4F4', // *Color of the grid lines.
                background: '#ffffff', // CSS color spec for background color
										// of grid.
                borderColor: '#ffffff', // CSS color spec for border around
										// grid.
                borderWidth: 0, // pixel width of border around grid.
                shadow: true, // draw a shadow for grid.
                shadowAngle: 0, // angle of the shadow. Clockwise from x axis.
                shadowOffset: 0, // offset from the line of the shadow.
                shadowWidth: 0, // width of the stroke for the shadow.
                shadowDepth: 0 // Number of strokes to make when drawing
								// shadow.
                // Each stroke offset by shadowOffset from the last.
            },
            highlighter: {
                show: true,
                showMarker: false,
                tooltipLocation: 'cursor',
                showTooltip: true,
                tooltipFade: false,
                tooltipAxes: 'l,x,y',
                useAxesFormatters: true,
                formatString: '<b>%s</b> <br>  %s <b> %s '
            }
        }
        return options;
    };
    
    // drawing chart from xmldata format
    // parameters (where to chart render, input values , user optional settings)
    function drawChartXML(chart, param, usersettings){
        var xmlDoc = parseXMLDocFromString(param);
        var details = xmlDoc.getElementsByTagName("DETAILS");
        var type = details[0].getElementsByTagName("TYPE")[0].firstChild.nodeValue;
        type = jQuery.trim(type);
        var xtitle = details[0].getElementsByTagName("XAXIS")[0].firstChild.nodeValue;
        var ytitle = details[0].getElementsByTagName("YAXIS")[0].firstChild.nodeValue;
        var stack = details[0].getElementsByTagName("STACK")[0].firstChild.nodeValue;
        stack = jQuery.trim(stack);
        if (stack == 'true') {
            stack = true;
        }
        else {
            stack = false;
        }
        var tab = dataTableXML(chart, param); // converting to html table
        $('#' + tab).css('display', 'none');
        
        var options = {
            stackSeries: stack,
            axes: {
                xaxis: {
                    label: xtitle
                },
                yaxis: {
                    label: ytitle
                }
            }
        }
        usersettings = $.extend(true, options, usersettings);
        // drawing chart by passing html table input
        drawChartFromTable(chart, tab, type, usersettings);
    };
    
    // drawing table from xml format data
    function drawTableXML(chart, param){
        var xmlDoc = parseXMLDocFromString(param);
        var elm = xmlDoc.getElementsByTagName("DATAVALUES");
        var elmTable = elm[0].getElementsByTagName("table")[0];
        var table = chart + 'newtable';
        elmTable.setAttribute("id", table);
        elmTable.setAttribute("class", "Datatable");
        elmTable.setAttribute('style', 'display:table');
        if (typeof XMLSerializer != "undefined") 
            str = (new XMLSerializer()).serializeToString(elmTable); // For
																		// FireFox
        else 
            if (elmTable.xml) 
                str = elmTable.xml;
            else 
                throw "XML.serialize is not supported or can't serialize " + elmTable; // for
																						// IE
        document.getElementById(chart).innerHTML = str;
    };
    
    
    // converting xml strind format to html table format
    function dataTableXML(chart, param){
    
        var xmlDoc = parseXMLDocFromString(param);
        var details = xmlDoc.getElementsByTagName("DETAILS");
        var elm = xmlDoc.getElementsByTagName("DATAVALUES");
        var str;
        var elmTable = elm[0].getElementsByTagName("table")[0];
        
        if (typeof XMLSerializer != "undefined") 
            str = (new XMLSerializer()).serializeToString(elmTable); // For
																		// FireFox
        else 
            if (elmTable.xml) 
                str = elmTable.xml;
            else 
                throw "XML.serialize is not supported or can't serialize " + elmTable; // for
																						// IE
        document.getElementById(chart).innerHTML = str;
        return elmTable.getAttribute("id");
    };
    
    
    // drawing chart from string data format
    function drawChartString(chart, type, title, xVal, yVal, usersettings){
    
        var tab = drawTableString(chart, title, xVal, yVal); // converting
																// string to
																// html data
																// format
        $('#' + tab).css('display', 'none');
        // drawing chart by passing html table input
        drawChartFromTable(chart, tab, type, usersettings);
        
    };
    
    // drawing table from string data format
    function drawTableString(chart, title, xVal, yVal){
        var xvalues = [];
        
        xvalues = xVal.split(",");
        var seriesname = [];
        var data = [];
        for (var i = 0; i < yVal.length; i++) {
            var yvalue = [];
            val = yVal[i];
            index = val.indexOf(",");
            name = val.substring(0, index);
            sval = val.substring(index + 1);
            yvalue = sval.split(",");
            seriesname.push(name);
            data.push(yvalue);
        }
        var row = [];
        var cell = [];
        var row_num = xvalues.length + 1;
        var cell_num = yVal.length + 1;
        
        tab = document.createElement('table');
        var tableStr = chart + 'newtable';
        tab.setAttribute('id', tableStr);
        tab.setAttribute("class", "Datatable");
        caption = document.createElement('caption');
        tbo = document.createElement('tbody');
        cont = document.createTextNode(title);
        caption.appendChild(cont);
        for (c = 0; c < row_num; c++) {
            row[c] = document.createElement('tr');
            
            for (k = 0; k < cell_num; k++) {
                if (k == 0) {
                    if (c == 0) {
                        cell[k] = document.createElement('th');
                        cont = document.createTextNode("Name");
                    }
                    else {
                        cell[k] = document.createElement('th');
                        cont = document.createTextNode(xvalues[c - 1]);
                    }
                }
                else 
                    if (c == 0) {
                        cell[k] = document.createElement('th');
                        cont = document.createTextNode(seriesname[k - 1]);
                    }
                    else {
                        cell[k] = document.createElement('td');
                        val = data[k - 1];
                        cont = document.createTextNode(val[c - 1]);
                    }
                
                cell[k].appendChild(cont);
                row[c].appendChild(cell[k]);
            }
            tbo.appendChild(row[c]);
        }
        tab.appendChild(tbo);
        tab.appendChild(caption);
        document.getElementById(chart).appendChild(tab);
        return tab.id;
    };
    
    
    // rendering the graph
    function drawChartFromTable(div, tableId, type, usersettings){
    	chartType = type;
        var table = document.getElementById(tableId);
        var title = $('caption', table).text();
        var rowlen = table.rows.length;
        var celllen = table.rows[0].cells.length;
        
        if (type == 'pie' || type == 'donut' || type == 'funnel') {
            options = setPieOptions();
            if (type == 'funnel') 
                options.seriesDefaults.renderer = $.jqplot.FunnelRenderer;
            if (type == 'pie') 
                options.seriesDefaults.renderer = $.jqplot.PieRenderer;
            if (type == 'donut') 
                options.seriesDefaults.renderer = $.jqplot.DonutRenderer;
            
            var name = $(table.rows[0].cells[1]).text();
            var piedata = [];
            var data = [];
            for (var i = 1; i < rowlen; i++) {
                var dataPair = [];
                dataPair.push($.trim($(table.rows[i].cells[0]).text()));
                var val = $.trim($(table.rows[i].cells[1]).text());
                dataPair[1] = parseFloat(val);
                piedata.push(dataPair);
            }
            data.push(piedata);
            options.title.text = title + "<br/>" + name;
        }
        else {
            options = setBarOptions();
            
            if (type == 'bar') 
                options.seriesDefaults.renderer = $.jqplot.BarRenderer;
            if (type == 'area') {
                options.stackSeries = true;
                options.seriesDefaults.fill = true;
                options.highlighter.showMarker = false;
                options.highlighter.useAxesFormatters = false;
                options.seriesDefaults.markerOptions.show = false;
            }
            
            
            options.series = [];
            cell = table.rows[0].cells;
            for (var j = 1; j < cell.length; j++) {
                var series = {};
                val = $.trim($(cell[j]).text());
                series.label = val;
                options.series.push(series);
            }
            var xval = [];
            var data = [];
            
            for (var j = 0; j < celllen; j++) {
                yval = [];
                for (var i = 1; i < rowlen; i++) {
                    if (j == 0) {
                        val = $.trim($(table.rows[i].cells[j]).text());
                        xval.push(val);
                    }
                    else {
                        val = $.trim($(table.rows[i].cells[j]).text());
                        yval.push(parseFloat(val));
                    }
                    
                }
                if (j > 0) 
                    data.push(yval);
            }
            
            
            options.axes.xaxis.ticks = xval;
            options.title.text = title;
        }
        if (usersettings) 
            usersettings = $.extend(true, options, usersettings);
        
        if(usersettings != null){
        	onclickMethod = usersettings.onClick;
         }
		 tooltipHandle(div);
        $.jqplot(div, data, options);
        
       
        
    };
    
    // drawing table for html table data input
    function drawTableFromTable(chart, tableId){
        var table = document.getElementById(tableId);
        var tableid = chart + 'newtable';
        table.setAttribute('id', tableid);
        table.setAttribute("class", "Datatable");
        table.setAttribute('style', 'display:table');
        document.getElementById(chart).appendChild(table);
        
    };
    // drawing metergauage chart
    function drawMeterGauageChart(div, ticks, label, interval, data){
        xval = [];
        xval = ticks.split(",");
        for (var i = 0; i < xval.length; i++) {
            xval[i] = parseFloat(xval[i]);
        }
        intervals = [];
        intervals = interval.split(",");
        for (var i = 0; i < intervals.length; i++) {
            intervals[i] = parseFloat(intervals[i]);
        }
        var options = {
            seriesDefaults: {
                renderer: $.jqplot.MeterGaugeRenderer,
                rendererOptions: {
                    showTickLabels: true,
                    label: label,
                    ticks: xval,
                    intervals: intervals,
                    intervalColors: ['#8FA8E0', '#92C2A0', '#6BBDE9', '#B7D2C8', '#93BEEE', '#419EEB', '#5ea650', '#A47D7C', '#B5CA92']
                }
            }
        }
        
        $.jqplot(div, [[data]], options);
    };
    // toggle function for graph and table
    function toggle(button, chart, table, val){
        var tableid = '#' + table;
        var chartid = '#' + chart;
        if (val == 'View Graph') {
            $(tableid).hide();
            $(chartid).show();
            document.getElementById(button).value = 'View Table';
        }
        else {
            $(chartid).hide();
            $(tableid).show();
            document.getElementById(button).value = 'View Graph';
        }
    }
    
    function SingleClickEvent(gridpos, neighbor, series) {

		if (onclickMethod != "false") {
			
			if (chartType == 'pie' || chartType == 'donut'
					|| chartType == 'funnel') {
				onclickMethod.call(this, gridpos, neighbor);
			} else {
				onclickMethod.call(this, gridpos, neighbor, series);
			}
		}
	}
    function tooltipHandle(chart){
		var ttipid = chart+'_ttip';
		var borderid = chart+'_ttipborder';
        $('#' + chart).mousemove(function(event){
            if (!tooltip) {
				document.getElementById(ttipid).style.display = "none";
				document.getElementById(borderid).style.display = "none";
                //$("#ttip,#ttipborder").css("display", "none");
            }
            else {
				document.getElementById(ttipid).style.display = "block";
				document.getElementById(borderid).style.display = "block";
               // $("#ttip,#ttipborder").css("display", "block");
                tooltip = false;
            }
        });
		
		$('#'+chart).click(function(event){
			document.getElementById(ttipid).style.display = "none";
			document.getElementById(borderid).style.display = "none";
		});
		
    }
    
    function tooltipDisplay(val){
        tooltip = val;
    }
    // exposed
    localchart.drawChartXML = drawChartXML;
    localchart.drawTableXML = drawTableXML;
	
    localchart.drawChartString = drawChartString;
    localchart.drawTableString = drawTableString;
	
    localchart.drawChartFromTable = drawChartFromTable;
    localchart.drawTableFromTable = drawTableFromTable;
    
    localchart.drawMeterGauageChart = drawMeterGauageChart;
    localchart.toggle = toggle;
    localchart.SingleClickEvent = SingleClickEvent;
	localchart.tooltipDisplay = tooltipDisplay;
	
    // gloobal
    global.yichartlib = localchart; // assign local object to global object
})(window);
