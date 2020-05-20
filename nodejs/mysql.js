//mysql 예제.
var mysql = require('mysql');
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '991206',
  database : 'user'
});

//mysql과 연결
connection.connect();
 

// 첫번째 인자: 쿼리문, 두번째 인자: 콜백함수(응답을 받으면 실행하는 함수)
connection.query('SELECT * from login', function (error, rows, fields) {
    var data;
    for (var i in rows){  
        data += rows[i].id + " " + rows[i].password + "\n";   
    } 
    console.log(data);
  }); 
  
//연결 끊기
connection.end();
