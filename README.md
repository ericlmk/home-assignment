# Home Assignment

## Task  
You need to implement a rest service that accepts requests to validate a bank account and returns information if the requested account is valid. The service doesn't store any data but instead sends validation requests to other account data providers, aggregates this data and returns to the client. You DO NOT need to implement data providers, only the service. You can assume those data providers exist and you are given their names and api urls.


Request has one mandatory field - bank account number.
It may have an optional field "providers", which is a list of names of data providers used to query information. If this field is empty then all providers defined in the application's configuration must be used.

Data providers are defined in the application's configuration and must not be written in the code. Every provider accepts requests with one mandatory field - bank account number. Data providers return messages with only one field "isValid", which is boolean.

You don't need to implement data providers, only the service that polls data providers.

Response is an array of objects, each object has two fields: provider and isValid. Provider is a string and is the name of a data provider, isValid is a boolean value that data provider returned.

## Requirements:

1. Rest api, all messages in json.
2. Spring boot app.
3. Sufficient tests to demonstrate the app is working correctly.
4. Data providers' url are set as properties and must not be stored in code. Demonstrate how the urls can be set for production and non-production environments.
5. The rest api should return response within 2 seconds. It is guaranteed that all external data providers will return data within 1 second.
6. The code is uploaded to github. Please share a link to the code with your recruiter.

You don't need to implement authentication, authorization, swagger documentation; no need to deploy it anywhere, passing tests and running the app locally more than enough.

It is expected that this task will take 1-2 hours to implement at maximum.

## Test Sample

1. Positive Case - All request information are provided
```
curl --location --request POST 'localhost:8080/account/validate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountNumber": "12345678",
    "providers": [
        "provider1",
        "provider2",
        "provider3"
    ]
}'
```

2. Positive Case - Not providing the provider list
```
curl --location --request POST 'localhost:8080/account/validate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountNumber": "12345678"
}'
```

3. Negative Case - No request body
```
curl --location --request POST 'localhost:8080/account/validate' \
--header 'Content-Type: application/json' \
--data-raw '{}'
```