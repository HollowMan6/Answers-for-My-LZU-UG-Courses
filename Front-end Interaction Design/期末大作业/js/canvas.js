var canvas = document.getElementById('canvas');
var c = canvas.getContext('2d');
c.lineWidth = 10;
c.strokeStyle = "#A52A2A"
c.moveTo(200, 107);
c.lineTo(150, 50);
c.lineTo(100, 100);
c.lineTo(200, 200);
c.lineTo(300, 100);
c.lineTo(250, 50);
c.lineTo(200, 100);
c.stroke();