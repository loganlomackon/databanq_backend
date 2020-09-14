
Body格式如下, 
重點在於
"type": ["VerifiableCredential", "DeviceTransfer"],
與credentialSubject,
物件中須包含from_did, to_did, 和device_did

```
{
"@context": [
"https://www.w3.org/2018/credentials/v1",
"https://www.w3.org/2018/credentials/examples/v1" ],
// specify the identifier for the credential
"id": "https://baasid.com.tw/credentials/X8gdLLZJaNtsrnEip/ADr6CIJopwPbRCl+TM9jhIsCM",
// the entity that issued the credential
"issuer": "https://baasid.com.tw/issuers/565049",
// when the credential was issued
"issuanceDate": "2020-01-01T19:73:24Z",

// the credential types, which declare what data to expect in the credential
"type": ["VerifiableCredential", "DeviceTransfer"],

"credentialSubject": {
  "from_did": "did:sidetree: hGYUj6aRTUYAAz+Q2jV8Kjg5RHxnUFejS9PPDFln1uc",
  "to_did": "did:sidetree: qHvoB38AyqYNxFImQGdNSNiY/GrXR1E7E3yHVIALVTI",
  "device_did": "did:sidetree: qHvoB38AyqYNxFImQGdNSNiY/GrXR1E7E3yHVIALVTI",
},

// digital proof that makes the credential tamper-evident // see the NOTE at end of this section for more detail "proof": {
// the cryptographic signature suite that was used to generate the signature
"type": "RsaSignature2018",
// the date the signature was created
"created": "2020-01-01T19:73:24Z ",
// purpose of this proof
"proofPurpose": "assertionMethod",
// the identifier of the public key that can verify the signature
"verificationMethod": "https://baasid.com.tw/issuers/565049/keys/1",
// the digital signature value
"jws": "eyJhbGciOiJSUzI1NiIsImI2NCI6ZmFsc2UsImNyaXQiOlsiYjY0Il19..TCYt5X sITJX1CxPCT8yAV-TVkIEq_PbChOMqsLfRoPsnsgw5WEuts01mq-pQy7UJiN5mgRxD-WUc X16dUEMGlv50aqzpqh4Qktb3rk-BuQy72IFLOqV0G_zS245-kronKb78cPN25DGlcTwLtj PAYuNzVBAh4vGHSrQyHUdBBPM"
} 
}
```
