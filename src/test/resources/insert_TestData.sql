use `poseiden`;
TRUNCATE bidlist ;
TRUNCATE curvepoint;

/*insert bidList*/
INSERT INTO `bidlist` (`account`, `type`, `bidQuantity`)
VALUES ( 'Account to Update',    'Type',    25); /*id=1*/

INSERT INTO `bidlist` (`account`, `type`, `bidQuantity`)
VALUES ( 'Account to Delete',    'Type',    50);/*id=2*/

/*insert CurvePoint*/

INSERT INTO `curvepoint`( `CurveId`, `term`, `value`)
VALUES (41,15.5,568.2)/*id=1*/;

INSERT INTO `curvepoint`( `CurveId`, `term`, `value`)
VALUES (42,150,1452)/*id=2*/;
