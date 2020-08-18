**Table of Contents**

- [User endpoints](#user-endpoints)
  - [Sign up](#sign-up)
  - [Login](#login)
- [Pairing endpoints](#pairing-endpoints)
  - [Pairing](#pairing)
- [Device endpoints](#device-endpoints)
  - [Transfer](#transfer)
  - [Transfer history](#transfer-history)


  
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
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
product_type | NO | Device product type.
mac_address | NO | Device mac address.
device_name | NO | Device name.

**Response RESULT:**
```
{
  "user_did": "abc123",
  "device_did": "xyz123"
}
```

## Device endpoints
### Transfer
```
POST /api/device/transfer
```
Transfer ownership of a device to another user.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
sender_did | YES | Device sender DID.
receiver_did | YES | Device receiver DID.
device_did | YES | Device DID.

**Response RESULT:**
```
{
  "user_did": "abc123",
  "device_did": "xyz123"
}
```

### Transfer history
```
POST /api/device/transfer_history
```
Transfer ownership of a device to another user.

**Parameters:**

Name | Mandatory | Description
------------ | ------------ | ------------
user_did | YES | User DID.
device_did | YES | Device DID.

**Response RESULT:**
```
{
  "history": "Factory->A; A->B;"
}
```

