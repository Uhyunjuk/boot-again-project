-- article 데이터
INSERT INTO article(title, content) VALUES('가가가', '111');
INSERT INTO article(title, content) VALUES('나나나', '222');
INSERT INTO article(title, content) VALUES('다다다', '333');

INSERT INTO article(title, content) VALUES('여러분의 인생 영화는 무엇인가요?', '댓글로 달아주세요');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?', '댓글 고고고');

-- coffee 데이터
INSERT INTO coffee(name, price) VALUES('아메리카노', '4500');
INSERT INTO coffee(name, price) VALUES('라떼', '5000');
INSERT INTO coffee(name, price) VALUES('카페 모카', '5500');

-- comment 데이터
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'mark', '스파이더맨');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'jeno', '파묘');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'fullSun', '올드보이');

INSERT INTO comment(article_id, nickname, body) VALUES (5, 'fullSun', '장어덮밥');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'mark', '피자');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'jeno', '스키야끼');

INSERT INTO comment(article_id, nickname, body) VALUES (6, 'fullSun', '축구');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'jeno', '넷플릭스 시청');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'mark', '작곡하기');




