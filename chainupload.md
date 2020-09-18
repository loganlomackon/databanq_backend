POST https://chain-test.baasid.com.tw/iotapi/v1/blockchain/vcs <br/>
Parameters: <br/>
Request body:<br/>
```
{
“ownerId”: “did:sidetree:1314520#abmric33xm”,
“type”: “ownership”,
“credential”: “….” // Base64 encoded string of VC
}
```
Reponse code: <br/>
200 	OK <br/>
500 	gateway error <br/>
Response body: <br/>
```
{
	“result”: “OK”,
	“txId” : “…”,
	“msg” : “”
}
```

Get https://chain-test.baasid.com.tw/iotapi/v1/blockchain/vcs/{txId} <br/>
Request body: <br/>
Response code: <br/>
200		OK <br/>
500		gateway error <br/>
Response body: <br/>
```
{
	“result”: “OK”,
	“txId” : “…”,
	“payload” : 
		{
			“ownerId”: “did:sidetree:1314520#abmric33xm”,
“type”: “ownership”,
“credential”: “….” // Base64 encoded string of VC
		}
}
```
