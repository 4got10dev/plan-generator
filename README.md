# Plan Generator [![CircleCI](https://circleci.com/gh/4got10dev/plan-generator/tree/master.svg?style=svg)](https://circleci.com/gh/4got10dev/plan-generator/tree/master)
Application provide possibility to generate pre-calculated repayment plans throughout the lifetime of a loan.

## How to use
Project already has maven wrapper (so you don't need to have it installed). 
All other dependencies will be downloaded by it.

There are 3 maven profiles that creating one of 2 applications that work following modes:
- **All** "all" this profile will build both console and web-service application
- **Console** "console" (default one) this application 
- **Web Service** "ws" maintain new request and result for each addPart call as a new record
  
### Execution
Execute maven build (It may take a while in the first run):
```sh
# console application
$ ./mvnw install
```
```sh
# web-service application
$ ./mvnw install -Pws
```
```sh
# console and web-service applications
$ ./mvnw install -Pall
```

After the message of BUILD SUCCESS, the application artifacts will be available under **artifacts** folder (console-x.x.jar or web-service-x.x.jar):

### Usage example

#### Console
```sh
$ java -jar artifacts/console-1.0.jar 
Welcome to Plan Generator!
Please provide Loan Details:
Loan Amount (#.##):
$ 5000
Nominal Interest Rate in percents (#.##): 
$ 5
Duration - number of instalments in months (#):
$ 24
Date of Disbursement/Payout (dd.mm.yyyy):
$ 01.01.2020
-----------------------------------------Repayment Plan:-----------------------------------------
        Date         Annuity       Principal        Interest         Initial       Remaining
                                                                      Outstanding Principal
-------------------------------------------------------------------------------------------------
  2020-01-01          219.36          198.52           20.83         5000.00         4801.48
  2020-02-01          219.36          199.35           20.01         4801.48         4602.13
...
  2021-12-01          219.36          218.45            0.91          218.45            0.00
-------------------------------------------------------------------------------------------------
```
#### Web service
```sh
$ java -jar artifacts/web-service-1.0.jar
```
```sh
$ curl -X POST http://localhost:8787/generate-plan \
  -H "Content-type: application/json" \
  -d '{"loanAmount": "5000","nominalRate": "5.0","duration": 24,"startDate": "2018-01-01"}'
[
  {
    "date": "2018-01-01",
    "borrowerPaymentAmount": "219.36",
    "principal": "198.52",
    "interest": "20.83",
    "initialOutstandingPrincipal": "5000.00",
    "remainingOutstandingPrincipal": "4801.48"
  },
  {
    "date": "2018-02-01",
    "borrowerPaymentAmount": "219.36",
    "principal": "199.35",
    "interest": "20.01",
    "initialOutstandingPrincipal": "4801.48",
    "remainingOutstandingPrincipal": "4602.13"
  },
...
]
```

## Tech stack
- Backend
    - Java 8
- Web service
     - Spark 
     - Gson
- Maven



