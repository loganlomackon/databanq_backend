**Table of Contents**

- [User endpoints](#user-endpoints)
  - [Sign up](#sign-up)
  - [Login](#login)
- [Pairing endpoints](#pairing-endpoints)
  - [Pairing](#pairing)
- [Device endpoints](#device-endpoints)
  - [Transfer](#transfer)
  - [Transfer history](#transfer-history)


  
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 Test site: 35.221.155.56:9201
 Please send the following parameters with JSON file.
 
 
## User endpoints
### Sign up
```
POST /api/user/signup
```
Create new user account with DID.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
did | YES | User DID.
email | NO | User email.
phone | NO | User cellphone.

**Response RESULT:**
```
{
  "did": "abc123",
  "email": "abc@gmail.com",
  "phone": "0911222333"
}
```


### Login
```
POST /api/user/login
```
Login with DID.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
did | YES | User DID.

**Response RESULT:**
```
{
  "did": "abc123",
  "email": "abc@gmail.com",
  "phone": "0911222333"
}
```

### Delete
```
Delete /api/user
```
Delete with DID.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
did | YES | User DID.

**Response RESULT:**
200 OK


## Pairing endpoints
### Pairing
```
POST /api/pairing
```
Pairing a device with user.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
user_did | YES | User DID.
device_did | YES | Device DID.
product_id | NO | Device product ID.
product_type | NO | Device product type. Integer.
mac_address | NO | Device mac address.
device_name | NO | Device name.
warranty_period | NO | When device was paired at first time, the warranty expiry date is: CURRENT_DATE+(warranty_period days).

**Response RESULT:**
```
{
  "user_did": "abc123",
  "device_did": "xyz123"
}
```

## Device endpoints
### Get device info
```
GET /api/device/{DID}
```
Get device info by DID.

**Response RESULT:**
```
{
  "did": "xyz123", 
  
  "device_name": "DeviceName",
  "product_id": "productId", 
  "product_type": "123", 
  "mac_address": "00:11:22:xxx",
  "warranty_period": "180",
  
  "owner_did": "abc123",
  "warranty_expiry_date: "2020-11-30"
}

```


### Delete device
```
Delete /api/device
```
Delete with DID.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
did | YES | Device DID.

**Response RESULT:**
200 OK


### Transfer
```
POST /api/device/transfer
```
Transfer ownership of a device to another user.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
from_did | YES | Device sender DID.
to_did | YES | Device receiver DID.
device_did | YES | Device DID.
tx_id | YES | Transaction ID.

**Response RESULT:**
```
{
  "user_did": "abc123",
  "device_did": "xyz123"
}
```

### Transfer history
```
POST /api/device/transfer/history
```
Check ownership transfer history of a device.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
user_did | YES | User DID.
device_did | YES | Device DID.

TODO: There is no auth mechanism to check current user now. 
If auth is implemented, user_did will be removed.

**Response RESULT:**
```
{
  "history": [
    {
      timestamp: "12345678",
      "tx_id": "abcxyz",
      
      "from_user": {
        "did": "abc123",
        "phone": "0911222333",
        "email": "abc@gmail.com",
      },
      "to_user": {
        "did": "xyz123",
        "phone": "0944555666",
        "email": "xyz@gmail.com",
      },
    }
  ]
}
```

