# SGasStations
Проектот се состои од веб апликација која ќе ги прикажува најблиските бензински станици во однос на моменталната локација на корисникот,
податоците се превземени од <a href="http://download.geofabrik.de/europe/macedonia.html">OpenStreetMaps</a>, дополнително ќе содржи навигација преку <a href="https://www.google.com/maps">google maps</a>. Податоците се чуваат со помош на MongoDB (нерелациона база на податоци).
## Користење на скриптата за внес на податоци (Linux)
##### Потребни инсталации
<ul>
  <li>wget  - (По стандард го има со инсталација на линукс)</li>
  <li>MongoDB -  (Поставен на порта 27017 - <b>default</b>)</li>
</ul>
<p>
Дополнително податоците се чуваат во база <b>local</b>, со име на колекцијата <b>gasStations</b>.</br>
Главната shell скрипта е <b>data.sh</b>, оваа скрипта ја повикува скриптата <b>getdata.sh</b>, служи за пронаоѓање на потребните податоци и претварање во соодветен формат, која се претпоставува дека е во ист директориум.
Исто така не се потребни дополнителни фајлови, скиптата директно ја спушта потребната датотека и не ја зачувува туку го испраќа текстот на следната команда.
</p>
<p>
  За проверка на функционалност на скриптата (бидејќи одзема многу време), можи да се промени на линија 28, наместо да ги запишува само записите што содржат <b>fuel</b> да запишува записи што содржат <b>peak</b>, или замена на условот во заградите со true, исто така може и комплетно да се елиминира if наредбата.
</p
