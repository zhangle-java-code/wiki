# 统计单个电子书
select count(1), SUM(view_count) from doc where ebook_id = 2;


select
    ebook_id,
    name,
    count(1) doc_count,
    SUM(view_count) view_count ,
    SUM(vote_count) vote_count
from
    doc
group by ebook_id;

# 两个表相互关联
update ebook t1,(select ebook_id,name,count(1) doc_count, SUM(view_count) view_count ,SUM(vote_count) vote_count from doc group by ebook_id) t2
set t1.doc_count = t2.doc_count,t1.view_count=t2.view_count,t1.vote_count=t2.vote_count
where t1.id = t2.ebook_id;



select id, curdate(), 0, 0, 0, 0 from ebook;


insert into ebook_snapshot(ebook_id, `date`, view_count, vote_count, view_increase, vote_increase)
select t1.id, curdate(), 0, 0, 0, 0
from ebook t1
where not exists(select 1
                 from ebook_snapshot t2
                 where t1.id = t2.ebook_id
                   and t2.`date` = curdate());
