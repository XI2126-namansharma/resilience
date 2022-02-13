# resilience

## URL:
#### localhost:8080/retry/apple

## Outputs:
#### Price of apple is $84
#### Price of apple is $59

## Logs:

#### Service down
###### 2022-02-13 18:30:50.785  INFO 10304 --- [nio-8080-exec-2] c.p.r.s.impl.ResilienceServiceImpl       : Retry attempt 1 for gettingPriceForapple
###### Service up
###### 2022-02-13 18:30:51.300  INFO 10304 --- [nio-8080-exec-2] c.p.r.s.impl.ResilienceServiceImpl       : Price of apple is $84
###### Service up
###### 2022-02-13 18:31:16.555  INFO 10304 --- [nio-8080-exec-3] c.p.r.s.impl.ResilienceServiceImpl       : Price of apple is $59
