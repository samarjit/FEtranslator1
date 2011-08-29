/**
 * Copyright (c) 2009 - 2010 Chris Leonello
 * jqPlot is currently available for use in all personal or commercial projects 
 * under both the MIT and GPL version 2.0 licenses. This means that you can 
 * choose the license that best suits your project and use it accordingly. 
 *
 * The author would appreciate an email letting him know of any substantial
 * use of jqPlot.  You can reach the author at: chris dot leonello at gmail 
 * dot com or see http://www.jqplot.com/info.php .  This is, of course, 
 * not required.
 *
 * If you are feeling kind and generous, consider supporting the project by
 * making a donation at: http://www.jqplot.com/donate.php .
 *
 * Thanks for using jqPlot!
 * 
 */
(function($) {
	$.jqplot.eventListenerHooks.push( [ 'jqplotMouseMove', handleMove ]);
	$.jqplot.eventListenerHooks.push( [ 'jqplotClick', handleClick ]);
//	$.jqplot.eventListenerHooks.push( [ 'jqplotRightClick', handleRightClick ]);
	
	/**
	 * Class: $.jqplot.Highlighter
	 * Plugin which will highlight data points when they are moused over.
	 * 
	 * To use this plugin, include the js
	 * file in your source:
	 * 
	 * > <script type="text/javascript" src="plugins/jqplot.highlighter.js"></script>
	 * 
	 * A tooltip providing information about the data point is enabled by default.
	 * To disable the tooltip, set "showTooltip" to false.
	 * 
	 * You can control what data is displayed in the tooltip with various
	 * options.  The "tooltipAxes" option controls wether the x, y or both
	 * data values are displayed.
	 * 
	 * Some chart types (e.g. hi-low-close) have more than one y value per
	 * data point. To display the additional values in the tooltip, set the
	 * "yvalues" option to the desired number of y values present (3 for a hlc chart).
	 * 
	 * By default, data values will be formatted with the same formatting
	 * specifiers as used to format the axis ticks.  A custom format code
	 * can be supplied with the tooltipFormatString option.  This will apply 
	 * to all values in the tooltip.  
	 * 
	 * For more complete control, the "formatString" option can be set.  This
	 * Allows conplete control over tooltip formatting.  Values are passed to
	 * the format string in an order determined by the "tooltipAxes" and "yvalues"
	 * options.  So, if you have a hi-low-close chart and you just want to display 
	 * the hi-low-close values in the tooltip, you could set a formatString like:
	 * 
	 * > highlighter: {
	 * >     tooltipAxes: 'y',
	 * >     yvalues: 3,
	 * >     formatString:'<table class="jqplot-highlighter">
	 * >         <tr><td>hi:</td><td>%s</td></tr>
	 * >         <tr><td>low:</td><td>%s</td></tr>
	 * >         <tr><td>close:</td><td>%s</td></tr></table>'
	 * > }
	 * 
	 */

	$.jqplot.Highlighter = function(options) {
		// Group: Properties
		//
		//prop: show
		// true to show the highlight.
		this.show = $.jqplot.config.enablePlugins;
		// prop: markerRenderer
		// Renderer used to draw the marker of the highlighted point.
		// Renderer will assimilate attributes from the data point being highlighted,
		// so no attributes need set on the renderer directly.
		// Default is to turn off shadow drawing on the highlighted point.
		this.markerRenderer = new $.jqplot.MarkerRenderer( {
			shadow : false
		});
		// prop: showMarker
		// true to show the marker
		this.showMarker = true;
		// prop: lineWidthAdjust
		// Pixels to add to the lineWidth of the highlight.
		this.lineWidthAdjust = 2.5;
		// prop: sizeAdjust
		// Pixels to add to the overall size of the highlight.
		this.sizeAdjust = 5;
		// prop: showTooltip
		// Show a tooltip with data point values.
		this.showTooltip = true;
		// prop: tooltipLocation
		// Where to position tooltip, 'n', 'ne', 'e', 'se', 's', 'sw', 'w', 'nw'
		this.tooltipLocation = 'nw';
		// prop: fadeTooltip
		// true = fade in/out tooltip, flase = show/hide tooltip
		this.fadeTooltip = true;
		// prop: tooltipFadeSpeed
		// 'slow', 'def', 'fast', or number of milliseconds.
		this.tooltipFadeSpeed = "fast";
		// prop: tooltipOffset
		// Pixel offset of tooltip from the highlight.
		this.tooltipOffset = 2;
		// prop: tooltipAxes
		// Which axes to display in tooltip, 'x', 'y' or 'both', 'xy' or 'yx'
		// 'both' and 'xy' are equivalent, 'yx' reverses order of labels.
		this.tooltipAxes = 'both';
		// prop; tooltipSeparator
		// String to use to separate x and y axes in tooltip.
		this.tooltipSeparator = ', ';
		// prop: useAxesFormatters
		// Use the x and y axes formatters to format the text in the tooltip.
		this.useAxesFormatters = true;
		// prop: tooltipFormatString
		// sprintf format string for the tooltip.
		// Uses Ash Searle's javascript sprintf implementation
		// found here: http://hexmen.com/blog/2007/03/printf-sprintf/
		// See http://perldoc.perl.org/functions/sprintf.html for reference.
		// Additional "p" and "P" format specifiers added by Chris Leonello.
		this.tooltipFormatString = '%.5P';
		// prop: formatString
		// alternative to tooltipFormatString
		// will format the whole tooltip text, populating with x, y values as
		// indicated by tooltipAxes option.  So, you could have a tooltip like:
		// 'Date: %s, number of cats: %d' to format the whole tooltip at one go.
		// If useAxesFormatters is true, values will be formatted according to
		// Axes formatters and you can populate your tooltip string with 
		// %s placeholders.
		this.formatString = null;
		// prop: yvalues
		// Number of y values to expect in the data point array.
		// Typically this is 1.  Certain plots, like OHLC, will
		// have more y values in each data point array.
		this.yvalues = 1;
		// prop: bringSeriesToFront
		// This option requires jQuery 1.4+
		// True to bring the series of the highlighted point to the front
		// of other series.
		this.bringSeriesToFront = false;
		this._tooltipElem;
		this.isHighlighting = false;
		this.debug = false;
		//	this.tooltipCallback = null;
		$.extend(true, this, options);
	};

	var divId = "";
	// axis.renderer.tickrenderer.formatter

	// called with scope of plot
	$.jqplot.Highlighter.init = function(target, data, opts) {
		divId = target;
		var options = opts || {};
		// add a highlighter attribute to the plot
		this.plugins.highlighter = new $.jqplot.Highlighter(options.highlighter);
	};

	// called within scope of series
	$.jqplot.Highlighter.parseOptions = function(defaults, options) {
		// Add a showHighlight option to the series 
		// and set it to true by default.
		this.showHighlight = true;
	};

	// called within context of plot
	// create a canvas which we can draw on.
	// insert it before the eventCanvas, so eventCanvas will still capture events.
	$.jqplot.Highlighter.postPlotDraw = function() {
		this.plugins.highlighter.highlightCanvas = new $.jqplot.GenericCanvas();

		this.eventCanvas._elem.before(this.plugins.highlighter.highlightCanvas
				.createElement(this._gridPadding, 'jqplot-highlight-canvas',
						this._plotDimensions));
		var hctx = this.plugins.highlighter.highlightCanvas.setContext();

		var p = this.plugins.highlighter;
		//Added z-Index:1 as re-drawing overlaps on the tooltip, so let it be on a upper layer in the beginning
		p._tooltipElem = $('<div style="position:absolute;border:1px solid;z-index:2;-moz-border-radius: 10px;display:none" id="'+divId+'_ttipborder" ></div>');

		this.eventCanvas._elem.before(p._tooltipElem);
		p._tooltipElem
				.append('<div class="jqplot-highlighter-tooltipopacity"  style="-moz-border-radius: 10px;position:absolute;opacity:0.7;z-index:1;filter:alpha(opacity=70);width:100%;height:100%;margin: 0px; border: 0px none; padding: 0px;"></div>');
		p._tooltipElem
				.append(' <div class="jqplot-highlighter-tooltip" id="'+divId+'_ttip" style="-moz-border-radius: 10px;position:relative;display:block;z-index:2;width:inherit;height:inherit;margin: 0px; border: 0px none; padding: 5px;"></div>');

	};

	$.jqplot.preInitHooks.push($.jqplot.Highlighter.init);
	$.jqplot.preParseSeriesOptionsHooks.push($.jqplot.Highlighter.parseOptions);
	$.jqplot.postDrawHooks.push($.jqplot.Highlighter.postPlotDraw);

	function draw(plot, neighbor) {
		var hl = plot.plugins.highlighter;
		var s = plot.series[neighbor.seriesIndex];
		var smr = s.markerRenderer;
		var mr = hl.markerRenderer;
		mr.style = smr.style;
		mr.lineWidth = smr.lineWidth + hl.lineWidthAdjust;
		mr.size = smr.size + hl.sizeAdjust;
		var rgba = $.jqplot.getColorComponents(smr.color);
		var newrgb = [ rgba[0], rgba[1], rgba[2] ];
		var alpha = (rgba[3] >= 0.6) ? rgba[3] * 0.6 : rgba[3] * (2 - rgba[3]);
		mr.color = 'rgba(' + newrgb[0] + ',' + newrgb[1] + ',' + newrgb[2]
				+ ',' + alpha + ')';
		mr.init();
		if (neighbor.pointIndex != null && neighbor.pointIndex > -1)
			mr
					.draw(s.gridData[neighbor.pointIndex][0],
							s.gridData[neighbor.pointIndex][1],
							hl.highlightCanvas._ctx);
	}
	function HexToR(h) {
		return parseInt((cutHex(h)).substring(0, 2), 16)
	}
	function HexToG(h) {
		return parseInt((cutHex(h)).substring(2, 4), 16)
	}
	function HexToB(h) {
		return parseInt((cutHex(h)).substring(4, 6), 16)
	}
	function cutHex(h) {
		return (h.charAt(0) == "#") ? h.substring(1, 7) : h
	}

	function showTooltip(xyrelpos, plot, series, neighbor) {
		// neighbor looks like: {seriesIndex: i, pointIndex:j, gridData:p, data:s.data[j]}
		// gridData should be x,y pixel coords on the grid.
		// add the plot._gridPadding to that to get x,y in the target.
		var hl = plot.plugins.highlighter;
		var elem = hl._tooltipElem;

		//setting color
		var color;
		if (neighbor.pointIndex > -1 && series.highlightColors) {
			color = series.highlightColors[neighbor.pointIndex];
		} else
			color = series.color;

		var colorcomp = $.jqplot.computeHighlightColors(color);
		var colorcomp = $.jqplot.getColorComponents(color);
		var r = colorcomp[0];//HexToR(color);
		var g = colorcomp[1];//HexToG(color);
		var b = colorcomp[2];//HexToB(color);
		elem.css('border-color', 'rgb(' + parseInt(r - r * .2) + ','
				+ parseInt(g - g * .2) + ',' + parseInt(b - b * .2) + ')');
		//	elem.css('border-color',color);
		elem.css('border-width', "2px");

		// elem.css('background',"none repeat scroll 0 0 ");
		//elem.css('opacity','.5');
		if (hl.tooltipCallback && $.isFunction(hl.tooltipCallback)) {
			str = hl.tooltipCallback.call(this, xyrelpos, plot, series,
					neighbor);
		} else {

			if (hl.useAxesFormatters) {
				var xf = series._xaxis._ticks[0].formatter;
				var yf = series._yaxis._ticks[0].formatter;
				var xfstr = series._xaxis._ticks[0].formatString;
				var yfstr = series._yaxis._ticks[0].formatString;
				var str;
				var xstr;

				///////////samarjit////////////

				if (series._xaxis._ticks.length > 0
						&& neighbor.pointIndex != null) {
					if (series._xaxis.renderer.constructor == $.jqplot.DateAxisRenderer) {
						xstr = series._xaxis._ticks[neighbor.pointIndex].label;
					} else {
						xstr = series._xaxis._ticks[neighbor.pointIndex * 2 + 1].label;
					}
				}

				if (series._xaxis.ticks.length && neighbor.pointIndex != null
						&& xstr.length > 0) {
					var x = series._xaxis.ticks[neighbor.pointIndex];
					if (typeof x[0] == "array")
						xstr = series._xaxis.ticks[neighbor.pointIndex][1];
					else
						xstr = series._xaxis.ticks[neighbor.pointIndex];
				} else {
					xstr = xf(xfstr, neighbor.data[0]);
				}

				//xstr = (series.label) ? series.label + '<br>'+xstr  : xstr;	
				///////////////////////////////

				var ystrs = [];
				for ( var i = 1; i < hl.yvalues + 1; i++) {
					ystrs.push(yf(yfstr, neighbor.data[i]));
				}

				if (hl.debug) {
					if (window.console) {
						console.debug("highlighter tooltip xstr=" + xstr
								+ "\n ystrs=" + ystrs);
					} else {
						alert("highlighter tooltip xstr=" + xstr + "\n ystrs="
								+ ystrs);
					}
				}

				if (hl.formatString) {
					if (hl.tooltipAxes)
						if (hl.tooltipAxes.indexOf(",") > -1) {
							var labels = hl.tooltipAxes.split(",");
							var seq = new Array();
							for ( var i = 0; i < labels.length; i++) {
								if (labels[i] == 'l')
									seq[i] = series.label;

								if (labels[i] == 'x')
									seq[i] = xstr;
								if (labels[i] == 'y')
									seq[i] = ystrs;
							}
							seq.unshift(hl.formatString);
							str = $.jqplot.sprintf.apply($.jqplot.sprintf, seq);
						} else {
							switch (hl.tooltipAxes) {

							case 'both':
							case 'xy':
								ystrs.unshift(xstr);
								ystrs.unshift(hl.formatString);
								str = $.jqplot.sprintf.apply($.jqplot.sprintf,
										ystrs);
								break;
							case 'yx':
								ystrs.push(xstr);
								ystrs.unshift(hl.formatString);
								str = $.jqplot.sprintf.apply($.jqplot.sprintf,
										ystrs);
								break;
							case 'x':
								str = $.jqplot.sprintf.apply($.jqplot.sprintf,
										[ hl.formatString, xstr ]);
								break;
							case 'y':
								ystrs.unshift(hl.formatString);
								str = $.jqplot.sprintf.apply($.jqplot.sprintf,
										ystrs);
								break;
							default: // same as xy
								ystrs.unshift(xstr);
								ystrs.unshift(hl.formatString);
								str = $.jqplot.sprintf.apply($.jqplot.sprintf,
										ystrs);
								break;
							}
						}
				} else { //formatString = null useAxisFormatters = true
					if (hl.tooltipAxes)
						if (hl.tooltipAxes.indexOf(",") > -1) {
							var labels = hl.tooltipAxes.split(",");
							var seq = new Array();
							for ( var i = 0; i < labels.length; i++) {
								if (labels[i] == 'l')
									seq[i] = series.label;

								if (labels[i] == 'x')
									seq[i] = xstr;
								if (labels[i] == 'y')
									seq[i] = ystrs;
							}
							seq.unshift(hl.formatString);
							str = $.jqplot.sprintf.apply($.jqplot.sprintf, seq);
						} else {
							switch (hl.tooltipAxes) {
							case 'both':
							case 'xy':
								str = xstr;
								for ( var i = 0; i < ystrs.length; i++) {
									str += hl.tooltipSeparator + ystrs[i];
								}
								break;
							case 'yx':
								str = '';
								for ( var i = 0; i < ystrs.length; i++) {
									str += ystrs[i] + hl.tooltipSeparator;
								}
								str += xstr;
								break;
							case 'x':
								str = xstr;
								break;
							case 'y':
								str = '';
								for ( var i = 0; i < ystrs.length; i++) {
									str += ystrs[i] + hl.tooltipSeparator;
								}
								break;
							default: // same as 'xy'
								str = xstr;
								for ( var i = 0; i < ystrs.length; i++) {
									str += hl.tooltipSeparator + ystrs[i];
								}
								break;

							}
						}
				}
			} else { //useAxesFormatters = false
				var str;

				if (hl.tooltipAxes.indexOf(",") > -1) {
					var labels = hl.tooltipAxes.split(",");
					var seq = new Array();
					for ( var i = 0; i < labels.length; i++) {
						if (labels[i] == 'l')
							seq[i] = series.label;

						if (labels[i] == 'x')
							seq[i] = xstr;
						if (labels[i] == 'y')
							seq[i] = ystrs;
					}
					seq.unshift(hl.formatString);
					str = $.jqplot.sprintf.apply($.jqplot.sprintf, seq);
				} else {
					if (hl.tooltipAxes == 'both' || hl.tooltipAxes == 'xy') {
						str = $.jqplot.sprintf(hl.tooltipFormatString,
								neighbor.data[0])
								+ hl.tooltipSeparator
								+ $.jqplot.sprintf(hl.tooltipFormatString,
										neighbor.data[1]);
					} else if (hl.tooltipAxes == 'yx') {
						str = $.jqplot.sprintf(hl.tooltipFormatString,
								neighbor.data[1])
								+ hl.tooltipSeparator
								+ $.jqplot.sprintf(hl.tooltipFormatString,
										neighbor.data[0]);
					} else if (hl.tooltipAxes == 'x') {
						str = $.jqplot.sprintf(hl.tooltipFormatString,
								neighbor.data[0]);
					} else if (hl.tooltipAxes == 'y') {
						str = $.jqplot.sprintf(hl.tooltipFormatString,
								neighbor.data[1]);
					}
				}
			}
		} // callback for str

		if (hl.debug) {
			if (window.console) {
				console.debug("str=" + str);
			} else {
				alert("str=" + str);
			}
		}

		elem.find(".jqplot-highlighter-tooltip").html(str);
		//   elem.find(".jqplot-highlighter-tooltipopacity").html(str);
		if (hl.tooltipLocation == 'cursor') {
			var gridpos = {
				x : xyrelpos.x,
				y : xyrelpos.y
			};
		} else if (neighbor.gridData) {
			var gridpos = {
				x : neighbor.gridData[0],
				y : neighbor.gridData[1]
			};

		}

		var ms = 0;
		var fact = 0.707;
		if (series.markerRenderer.show == true) {
			ms = (series.markerRenderer.size + hl.sizeAdjust) / 2;
		}
		switch (hl.tooltipLocation) {
		case 'nw':
			var x = gridpos.x + plot._gridPadding.left - elem.outerWidth(true)
					- hl.tooltipOffset - fact * ms;
			var y = gridpos.y + plot._gridPadding.top - hl.tooltipOffset
					- elem.outerHeight(true) - fact * ms;
			break;
		case 'n':
			var x = gridpos.x + plot._gridPadding.left - elem.outerWidth(true)
					/ 2;
			var y = gridpos.y + plot._gridPadding.top - hl.tooltipOffset
					- elem.outerHeight(true) - ms;
			break;
		case 'ne':
			var x = gridpos.x + plot._gridPadding.left + hl.tooltipOffset
					+ fact * ms;
			var y = gridpos.y + plot._gridPadding.top - hl.tooltipOffset
					- elem.outerHeight(true) - fact * ms;
			break;
		case 'e':
			var x = gridpos.x + plot._gridPadding.left + hl.tooltipOffset + ms;
			var y = gridpos.y + plot._gridPadding.top - elem.outerHeight(true)
					/ 2;
			break;
		case 'se':
			var x = gridpos.x + plot._gridPadding.left + hl.tooltipOffset
					+ fact * ms;
			var y = gridpos.y + plot._gridPadding.top + hl.tooltipOffset + fact
					* ms;
			break;
		case 's':
			var x = gridpos.x + plot._gridPadding.left - elem.outerWidth(true)
					/ 2;
			var y = gridpos.y + plot._gridPadding.top + hl.tooltipOffset + ms;
			break;
		case 'sw':
			var x = gridpos.x + plot._gridPadding.left - elem.outerWidth(true)
					- hl.tooltipOffset - fact * ms;
			var y = gridpos.y + plot._gridPadding.top + hl.tooltipOffset + fact
					* ms;
			break;
		case 'w':
			var x = gridpos.x + plot._gridPadding.left - elem.outerWidth(true)
					- hl.tooltipOffset - ms;
			var y = gridpos.y + plot._gridPadding.top - elem.outerHeight(true)
					/ 2;
			break;
		case 'cursor':
			var x = xyrelpos.x + plot._gridPadding.left + hl.tooltipOffset
					+ fact * ms + 10;
			var y = xyrelpos.y + plot._gridPadding.top + hl.tooltipOffset
					+ fact * ms + 10;
			break;
		default: // same as 'nw'
			var x = gridpos.x + plot._gridPadding.left - elem.outerWidth(true)
					- hl.tooltipOffset - fact * ms;
			var y = gridpos.y + plot._gridPadding.top - hl.tooltipOffset
					- elem.outerHeight(true) - fact * ms;
			break;
		}
		elem.css('left', x);
		elem.css('top', y);
		if (hl.fadeTooltip) {
			// Fix for stacked up animations.  Thnanks Trevor!
			elem.stop(true, true).fadeIn(hl.tooltipFadeSpeed);
		} else {
			elem.show();
		}

	}

	function handleMove(ev, gridpos, datapos, neighbor, plot) {
			
			var hl = plot.plugins.highlighter;
			var c = plot.plugins.cursor;
		
		if (hl.show) {
			if (neighbor == null && hl.isHighlighting) {
				this.className='arrow';
				var ctx = hl.highlightCanvas._ctx;
				ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
				if (hl.fadeTooltip) {
					hl._tooltipElem.fadeOut(hl.tooltipFadeSpeed);
				} else {
					hl._tooltipElem.hide();
				}
				if (hl.bringSeriesToFront) {
					plot.restorePreviousSeriesOrder();
				}
				hl.isHighlighting = false;

			}

			if (neighbor != null && hl.tooltipLocation == 'cursor') {
				//  $('#mousedata').text( " neighbor "+plot.series[neighbor.seriesIndex].showHighlight +" isHighlighting " + hl.isHighlighting+"x="+gridpos.x+" y="+gridpos.y);
				//make it follow cursoe for that we need to repaint on every cursor move
				hl.isHighlighting = false;
			}
			if (neighbor != null
					&& plot.series[neighbor.seriesIndex].showHighlight
					&& !hl.isHighlighting) {
						
				this.className='hand';
				hl.isHighlighting = true;

				if (hl.showMarker) {
					draw(plot, neighbor);
				}
				if (hl.showTooltip && (!c || !c._zoom.started)) {
					 yichartlib.tooltipDisplay(true);
					showTooltip(gridpos, plot,
							plot.series[neighbor.seriesIndex], neighbor);
				}
				if (hl.bringSeriesToFront) {
					plot.moveSeriesToFront(neighbor.seriesIndex);
				}
			}
		}
	}

	function handleClick(ev, gridpos, datapos, neighbor, plot) {

		if (neighbor != null) {
			yichartlib.SingleClickEvent(gridpos, neighbor,
			plot.series[neighbor.seriesIndex]);
		}
	}
	
	

})(jQuery);