INSERT INTO GENERATOR (GEN_NAME) VALUES ('USER_GEN');
INSERT INTO GENERATOR (GEN_NAME) VALUES ('LEVEL_RESULT_GEN');
INSERT INTO GENERATOR (GEN_NAME) VALUES ('AUDIT_EVENT_GEN');

INSERT INTO USER (USER_KEY, USER_ID, USER_NAME, EMAIL)
	VALUES((SELECT MAX(GEN_VALUE) FROM GENERATOR WHERE GEN_NAME = 'USER_GEN'), 'id0', 'makseemka', 'makseemkadragun@gmail.com');
UPDATE GENERATOR SET GEN_VALUE = GEN_VALUE + 1 WHERE GEN_NAME = 'USER_GEN';
INSERT INTO USER (USER_KEY, USER_ID, USER_NAME, EMAIL)
	VALUES((SELECT MAX(GEN_VALUE) FROM GENERATOR WHERE GEN_NAME = 'USER_GEN'), 'id1', 'test1', 'test1@gmail.com');
UPDATE GENERATOR SET GEN_VALUE = GEN_VALUE + 1 WHERE GEN_NAME = 'USER_GEN';
INSERT INTO USER (USER_KEY, USER_ID, USER_NAME, EMAIL)
	VALUES((SELECT MAX(GEN_VALUE) FROM GENERATOR WHERE GEN_NAME = 'USER_GEN'), 'id2', 'test2', 'test2@gmail.com');
UPDATE GENERATOR SET GEN_VALUE = GEN_VALUE + 1 WHERE GEN_NAME = 'USER_GEN';
INSERT INTO USER (USER_KEY, USER_ID, USER_NAME, EMAIL)
	VALUES((SELECT MAX(GEN_VALUE) FROM GENERATOR WHERE GEN_NAME = 'USER_GEN'), 'id3', 'test3', 'test3@gmail.com');
UPDATE GENERATOR SET GEN_VALUE = GEN_VALUE + 1 WHERE GEN_NAME = 'USER_GEN';

INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.000.test.lvl');

INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.001.letsStart.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.002.littleBitHarder.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.003.needMore.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.004.double5.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.005.mushroomRain.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.006.saveUs.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.007.roulette.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.008.queues.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.009.straightFlush.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.010.chessboard.lvl');

INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.011.mushroomShower.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.012.waves.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.013.casinoRoyale.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.014.regularity.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.015.unsafePlace.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.016.unsafeRegularity.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.017.storm.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.018.repentance.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.019.highway.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.020.fibonacci.lvl');
INSERT INTO LEVEL (LEVEL_ID) VALUES ('ozo.021.tsunami.lvl');
