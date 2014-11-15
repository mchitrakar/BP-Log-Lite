package com.example.dailybp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class DataPainter {

	public GraphView getGraphView(Context applicationcontext) {
		DataController mydb;

		mydb = DataController.getDBController(applicationcontext);
		
		GraphView graphView = new LineGraphView(applicationcontext,
				"<--      time      -->" // heading			   	      
		);

	    ArrayList<TableBP> bplist = mydb.readBPList();
	    int number_of_data = bplist.size();
	    if(number_of_data%2!=0){
	    	//show only the even number
	    	//to avoid floating number display at x-axis
	    	number_of_data--;
	    }
		
    	GraphViewData[] dataUp = new GraphViewData[2];
    	GraphViewData[] dataDn = new GraphViewData[2];
    	dataUp[0] = new GraphViewData(0, 120);
 	    dataUp[1] = new GraphViewData(number_of_data-1, 120);
		GraphViewSeries seriesDiastolic = new GraphViewSeries("Diastolic curve", new GraphViewSeriesStyle(Color.rgb(200, 50, 00), 3), dataUp);
 	    dataDn[0] = new GraphViewData(0, 80);
 	    dataDn[1] = new GraphViewData(number_of_data-1, 80);
 	    GraphViewSeries seriesSystolic = new GraphViewSeries("Diastolic curve", new GraphViewSeriesStyle(Color.rgb(00, 250, 50), 3), dataDn);
	    
		graphView.addSeries(seriesSystolic);	
		graphView.addSeries(seriesDiastolic);
		
//		int last_record = mydb.getLastId();
//		TableBP[] bpdata = new TableBP[3];
//		bpdata[0] = mydb.getTable(1);
//	    bpdata[1] = mydb.getTable(last_record/2);
//	    bpdata[2] = mydb.getTable(last_record);
	    
	    GraphViewData[] mydataS = new GraphViewData[number_of_data];
	    for(int i =0;i<number_of_data;i++){
	    	mydataS[i] = new GraphViewData(i, bplist.get(i).systolic);
	    }
 
	    
	
 	    GraphViewSeries myseriesS = new GraphViewSeries("Diastolic curve", new GraphViewSeriesStyle(Color.rgb(50, 00, 250), 13), mydataS);
 	    graphView.addSeries(myseriesS);
			    
	    GraphViewData[] mydataD = new GraphViewData[number_of_data];
	    for(int i =0;i<number_of_data;i++){
	    	mydataD[i] = new GraphViewData(i, bplist.get(i).diastolic);
	    }
 	    
 	    GraphViewSeries myseriesD = new GraphViewSeries("Diastolic curve", new GraphViewSeriesStyle(Color.rgb(50, 00, 250), 13), mydataD);
 	    graphView.addSeries(myseriesD);
		
 	    String[] s = new String[number_of_data];
 	   for(int i =0;i<number_of_data;i++){
// 		   if( (i==0) || (i ==(number_of_data-1)/2) || (i == number_of_data-1)){
// 	 		   s[i]=bplist.get(i).time;			   
// 		   }else{
 			  s[i]=""; 
 		//   }
	    }
 	    graphView.setHorizontalLabels(s);
 	/*   
 	   graphView.setScrollable(true);
		graphView.setViewPort((number_of_data-6), 6);
//		graphView.setScalable(true);
*/
		
		return graphView;
	}

//	public GraphView getExampleView(Context applicationcontext) {
//		
//		// init example series data
//		GraphViewSeries exampleSeries = new GraphViewSeries(
//				new GraphViewData[] {
//
//				new GraphViewData(1, 2.0d), new GraphViewData(2, 1.5d),
//						new GraphViewData(3, 2.5d), new GraphViewData(4, 1.0d) });
//
//		GraphView graphView = new LineGraphView(applicationcontext,
//				"GraphViewDemo" // heading
//		);
//		graphView.addSeries(exampleSeries); // data
//
//		graphView.setHorizontalLabels(new String[] { "2 days ago", "yesterday",
//				"today", "tomorrow" });
//		graphView.setVerticalLabels(new String[] { "high", "middle", "low" });
//
//		return graphView;
//
//	}
	
	
//	public GraphView getDoubleSineView(Context applicationcontext) {
//		// first init data
//		// sin curve
//		int num = 150;
//		GraphViewData[] data = new GraphViewData[num];
//		double v=0;
//		for (int i=0; i<num; i++) {
//		  v += 0.2;
//		  data[i] = new GraphViewData(i, Math.sin(v));
//		}
//		GraphViewSeries seriesSin = new GraphViewSeries("Sinus curve", new GraphViewSeriesStyle(Color.rgb(200, 50, 00), 3), data);
//		 
//		// cos curve
//		data = new GraphViewData[num];
//		v=0;
//		for (int i=0; i<num; i++) {
//		  v += 0.2;
//		  data[i] = new GraphViewData(i, Math.cos(v));
//		}
//		GraphViewSeries seriesCos = new GraphViewSeries("Cosinus curve", new GraphViewSeriesStyle(Color.rgb(90, 250, 00), 3), data);
//		 
//		// random curve
//		num = 1000;
//		data = new GraphViewData[num];
//		v=0;
//		for (int i=0; i<num; i++) {
//		  v += 0.2;
//		  data[i] = new GraphViewData(i, Math.sin(Math.random()*v));
//		}
//		GraphViewSeries seriesRnd = new GraphViewSeries("Random curve", null, data);
//		 
//		
//		/*
//		 * create graph
//		 */
//		GraphView graphView = new LineGraphView(
//				applicationcontext
//		    , "GraphViewDemo"
//		);
//		// add data
//		graphView.addSeries(seriesCos);
//		graphView.addSeries(seriesSin);
//		graphView.addSeries(seriesRnd);
//		// optional - set view port, start=2, size=10
//		graphView.setViewPort(2, 10);
//		graphView.setScalable(true);
//		// optional - legend
//		graphView.setShowLegend(true);
//		 
//		return graphView;
//		
//	}

}
