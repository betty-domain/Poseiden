use `poseiden_test`;
TRUNCATE bidlist ;
TRUNCATE curvepoint;
TRUNCATE rating;
TRUNCATE rulename;

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

/*insert rating */
INSERT INTO `rating`( `moodysRating`, `sandPRating`, `fitchRating`, `orderNumber`)
VALUES ('moody Rating 1','','fitch rating 1',1);
INSERT INTO `rating`( `moodysRating`, `sandPRating`, `fitchRating`, `orderNumber`)
VALUES ('','sand Rating 2','fitch rating aertez jdhjk ',3);

/*insert rulename*/

INSERT INTO `rulename`( `name`, `description`, `json`, `template`, `sqlStr`, `sqlPart`)
VALUES ('name 1','description rule 1', null,null,null,null); /*id=1*/
INSERT INTO `rulename`( `name`, `description`, `json`, `template`, `sqlStr`, `sqlPart`)
VALUES ('name rule 2','description rule to delete', 'json','template','sqlStr','sqlPart');/*id=2*/


