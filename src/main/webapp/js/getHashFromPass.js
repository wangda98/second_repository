function getHashFromPass(content) {	
 var b = new Base64();
 	var  codes=['0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',  
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',   
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
	var base64Content=b.encode(content); 
	console.log("base64码为"+ base64Content)
	var array1 = base64Content.split(""); /*把字符串分割成数组*/ 
	var length=array1.length*2;
	var array2=new Array(length);//创建一个长度为2倍的数组用来扩展数组
	var array3=new Array(length);//创建一个长度为2倍的数组用来存放倒序数组	
	for(var i=0;i<array1.length;i++){
  	var n=	Math.random()*(codes.length) //取出一个随机数
  	 n=parseInt(n, 10)
		array2[i*2]=codes[n];
		array2[i*2+1]=array1[i]
	}
	for( i in array2){			//进行倒序
		 array3[i]=array2[length-i-1]
	}
		return array3;
}