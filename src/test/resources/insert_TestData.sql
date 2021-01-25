use `poseiden`;
TRUNCATE bidlist ;

INSERT INTO `bidlist` (`account`, `type`, `bidQuantity`)
VALUES ( 'Account to Update',    'Type',    25); /*id=1*/

INSERT INTO `bidlist` (`account`, `type`, `bidQuantity`)
VALUES ( 'Account to Delete',    'Type',    50);/*id=2*/
