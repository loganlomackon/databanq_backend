POST https://chain-test.baasid.com.tw/iotapi/v1/blockchain/vcs <br/>
Header required: <br/>
Content-Type: application/json<br/>
Host: <br/>
Request body:<br/>
```
{
	"type": "transfer",
	"from_did": "abc123",
	"to_did": "xyz123",
	"device_did": "123abc"
}
```
Reponse code: <br/>
200 	OK <br/>
500 	gateway error <br/>
Response body: <br/>
```
{
	"result": "OK",
	"txId": "…",
	"msg": ""
}
```

Note: <br/>
Postman auto-generated header "Content-Length" is NOT compatible <br/>
<br/>
Get https://chain-test.baasid.com.tw/iotapi/v1/blockchain/vcs/{txId} <br/>
Request body: <br/>
Response code: <br/>
200		OK <br/>
500		gateway error <br/>
Response body: <br/>
```
{
	"result": "OK",
	"txId" : "…",
	"payload" : {
		"type": "transfer",
		"from_did": "abc123",
		"to_did": "xyz123",
		"device_did": "123abc"
	}
}
```
