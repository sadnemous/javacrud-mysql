curl -X POST http://localhost:9008/getemployee \
-H "Content-Type: application/json" \
-d '{ "id": 1, }'

curl -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -X GET http://localhost:9008/getempsvc 2> /dev/null|python3 -mjson.tool 

#1>tmp.json  
#cat tmp.json|python3 -mjson.tool
echo ""

