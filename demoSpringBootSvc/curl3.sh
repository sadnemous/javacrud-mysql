#set -x
id=${1:-3}
curl -X POST http://localhost:9008/allemployees -H "Content-Type: application/json" -d "{ \"id\": \"${id}\" }" \
     2> /dev/null|python3 -mjson.tool 

echo ""

