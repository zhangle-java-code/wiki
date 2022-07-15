# 统计单个电子书, 一对多
select
    name,
    count(1) as count_total,
    SUM(view_count) as view_count_total
from doc where ebook_id = 2;


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
update
    ebook t1,
    (select ebook_id,
            name,
            count(1) doc_count,
            SUM(view_count) view_count ,
            SUM(vote_count) vote_count
     from doc
     group by ebook_id
     )
        t2
set t1.doc_count = t2.doc_count,
    t1.view_count=t2.view_count,
    t1.vote_count=t2.vote_count
where
    t1.id = t2.ebook_id;



select
    id,
    curdate(),
    0,
    0,
    0,
    0
from ebook;

# 方案一： ID连续
# 1. 为所有的电子生成一条今天的记录， 如果没有
# 2. 更新阅读数、总赞数
# 3. 更新今日阅读数、今日赞数


insert into
    ebook_snapshot(ebook_id, `date`, view_count, vote_count, view_increase, vote_increase)
# 1
select
    t1.id,
    curdate(),
    0,
    0,
    0,
    0
from ebook t1
# 2 如果存在1就能查出来
where not exists(
    select
        1
    from ebook_snapshot t2
    where t1.id = t2.ebook_id
      and t2.`date` = curdate()
    );
# 3
update
    ebook_snapshot t1,
    ebook t2
set
    t1.view_count = t2.view_count,
    t1.vote_count= t2.vote_count
where t1.`date` = curdate()
and t1.ebook_id = t2.id;
;

# 获取昨天数据
# interval 1 day
select
    t1.ebook_id,
    view_count,
    vote_count
from ebook_snapshot t1
where t1.`date` = date_sub(curdate(), interval 1 day);

# !获取增长数据（导致昨天没有的插入，功能没有）
update ebook_snapshot t1,
    (
        select
            ebook_id,
            view_count,
            vote_count
        from ebook_snapshot
        where `date` = date_sub(curdate(), interval 1 day)
    ) t2
set t1.view_increase = t1.view_count - t2.view_count,
    t1.vote_increase = t1.vote_count - t2.vote_count
where
    t1.ebook_id = t2.ebook_id
  and
    t1.`date` = curdate();

#!获取增长数据, 更新左边表，不知道右边有没有
update ebook_snapshot t1
    left join
    (
        select
            ebook_id,
            view_count,
            vote_count
        from ebook_snapshot
        where `date` = date_sub(curdate(), interval 1 day)
    ) t2
on t1.ebook_id = t2.ebook_id
set t1.view_increase = t1.view_count - ifnull(t2.view_count, 0),
    t1.vote_increase = t1.vote_count - ifnull(t2.vote_count, 0)
where t1.`date` = curdate();

