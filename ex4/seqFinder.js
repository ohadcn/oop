/**
 * this javascript file contain functions for finding sequences and generating scores to DNA sequences
 * Author:Ohad Cohen, ohadcn (at) cs.huji.ac.il
 * created for ex4 in OOP course
 */

/**
 * find sequences in the input, calculate scores and put all the output in it's place
 */
function findSequences(){

	resetResults();
	//check if the input is valid, if not there is no point in continuing
	if(!validateInput())
		return;

	document.getElementById("emptyResults").hide();
	document.getElementById("out").show();
	document.getElementById("errorsSection").hide();	

	var isReverse=document.getElementById("reverse").checked;
	var checkEnhancers = !document.getElementById("onlySilencers").checked;
	var checkSilencers = !document.getElementById("onlyEnhancers").checked;

	var enhancers=document.getElementById("enhancerList").value.toLowerCase().split(",");
	var silencers=document.getElementById("silencerList").value.toLowerCase().split(",");
	var eColor=document.getElementById("enhancersColor").value;
	var sColor=document.getElementById("silencersColor").value;
	var seq=document.getElementById("inputSeq").value.toLowerCase();
	var enhancersValue=new Number(document.getElementById("enhancerScore").value);
	var silencersValue=new Number(document.getElementById("silencerScore").value);
	var juncPos = new Number(document.getElementById("junctionPos").value)-1;
	removeDuplicates(enhancers);
	removeDuplicates(silencers);
	document.getElementById("enhancersSeqs").innerHTML=document.getElementById("enhancerList").value.toLowerCase()
		.replace(/,/g,"<hr>")+"<hr>Total:";
	document.getElementById("silencersSeqs").innerHTML=document.getElementById("silencerList").value.toLowerCase()
		.replace(/,/g,"<hr>")+"<hr>Total:";
		
	var score=0;
	document.getElementById("markedSequence").innerHTML=seq.addTags("char");

	if(checkEnhancers){
		document.getElementById("enhancersLine").show();
		score+=markAndCount(seq,enhancers,enhancersValue,juncPos,"char","enhancersTable",eColor,false);
	}
	else
		document.getElementById("enhancersLine").hide();


	if(checkSilencers){
		document.getElementById("silencersLine").show();
		score+=markAndCount(seq,silencers,silencersValue,juncPos,"char","silencersTable",sColor,true);
	}
	else
		document.getElementById("silencersLine").hide();

	document.getElementById("totalScore").value=score.toFixed(3);


	if(isReverse){
		showClass("reversed");
		seq=seq.reverse();
		var score=0;
		document.getElementById("revMarkedSequence").innerHTML=seq.addTags("revChar");

		if(checkEnhancers){
			score+=markAndCount(seq,enhancers,enhancersValue,juncPos,"revChar","revEnhancersTable",eColor,false);
		}	

		if(checkSilencers){
			score+=markAndCount(seq,silencers,silencersValue,juncPos,"revChar","revSilencersTable",sColor,true);
		}


		document.getElementById("revTotalScore").value=score.toFixed(3);
	}
	else
		hideClass("reversed");

	
}

/**
 * remove duplicating elements from an array
 * @param arr the array to remove duplicating elements from
 */
function removeDuplicates(arr){
	for(i in arr){
		if(arr.indexOf(arr[i])!=i)
			arr.splice(i,1);
	}
}

/**
 * reset the output sections
 */
function resetResults(){
	document.getElementById("enhancersTable").innerHTML="";
	document.getElementById("silencersTable").innerHTML="";	
	document.getElementById("revEnhancersTable").innerHTML="";
	document.getElementById("revSilencersTable").innerHTML="";	
}

/**
 * mark all the occurances of elements from <i>queries</i> in <i>seq</i>, assuming 
 * that <i>namingConversion</i> has the id conversion of the coresponding elements in the page,
 * marking the elements is done by coloring them or thier background with <i>color</i> color,
 * beside coloring this method count how many times each query in <i>queries</i> appears in <i>seq</i>
 * and output that to <i>counterID</i>,
 * beside that this function calculates the score of all the <i>queries</i> found, the score is calculated 
 * according to position <i>juncPos</i>.
 * @param {String} seq the sequence to look on
 * @param {Array} queries the queries to mark in the sequence
 * @param {Number} value the value of each founded query, for the score calculating
 * @param {Number} juncPos the position of the junction in the sequence, relevant for the score
 * @param {String} namingConversion the naming conversion for the id's of characters in the sequence when they
 * appear in the page
 * @param {String} counterID the id of the elements that holds the counters of founded queries
 * @param {String} color the color to mark with the founded queries
 * @param {Boolean} isBgColor setting this to true will make this function change the background color of the
 * element and not the color of the text itself
 * @returns {Number} the total score of all the occurances of the queries in the sequence
 */
function markAndCount(seq,queries,value,juncPos,namingConversion,counterID,color,isBgColor){
	var place=document.getElementById(counterID);
	var score=0;
	var totalCount=0;
	for(subSeq in queries){
		var count=0;
		var found=seq.indexOf(queries[subSeq]);
		while(found!=-1){
			score+= value/Math.abs(found-juncPos);
			count++;
			for(var i=found;i<(queries[subSeq].length+found);i++)
				if(isBgColor)
					document.getElementById(namingConversion+i).style.background=color;
				else
					document.getElementById(namingConversion+i).style.color=color;
			found=seq.indexOf(queries[subSeq],found+1);
		}
		totalCount+= count;
		place.innerHTML+=count+"<hr>";
	}
	place.innerHTML+=totalCount;
	return score;
}