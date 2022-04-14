import React, { useCallback, useRef, useState } from 'react';
import EmptyList from 'component/empty_list/emptyList';
import StudyList from 'component/study_list/studyList';
import useStudySearch from '../hooks/useStudySearch';

const ShowByDate = () => {
  const SHOW_BY_DATE = '-createdAt';
  const [pageNumber, setPageNumber] = useState(0);

  const observer = useRef();
 // todo 나중에 데이터 받아오는 것 확인
  // const { studyList, hasMore, loading } = useStudySearch( 
  //   SHOW_BY_DATE,
  //   pageNumber,
  //   setPageNumber,
  //   checked
  // );
  const studyList = [{
    id : 1,
    language : ["React","NodeJS"],
    isClosed : true,
    views : 56,
    likes : ['test1', 'test2'],
    totalLikes : 2,
    title : "최신 사이드 프로젝트 같이하실 분",
    content : "React, NodeJS로 진행하려고 합니다.",
    author : { image: '3j5idn.PNG', nickName: 'TEST' },
    createdAt : '2021-12-18T13:31:08.457Z',
    updatedAt : '2021-12-18T13:31:08.457Z'
  }, {
    id : 2,
    language : ["Vue","Spring"],
    isClosed : false,
    views : 105,
    likes : [],
    totalLikes : 20,
    title : "최신 Toy Project 스터디원 모집합니다.",
    content : "Vue와 Spring으로 진행하려고 합니다.",
    author : { image: '3j5idn.PNG', nickName: '테스트' },
    createdAt : '2021-12-18T13:31:08.457Z',
    updatedAt : '2021-12-18T13:31:08.457Z'
  }];
  const hasMore = false;
  const loading = false;

  const lastStudyElementRef = useCallback(
    (node) => {
      if (loading) return;
      if (observer.current) observer.current.disconnect();
      observer.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && hasMore) {
          setPageNumber((prevPageNumber) => prevPageNumber + 20);
        }
      });
      if (node) observer.current.observe(node);
    },
    [loading, hasMore]
  );

  return (
    <>
      {studyList.length === 0 ? ( //로딩 일 경우도 추가 !loading && studyList.length === 0 ?
        <EmptyList />
       ) : ( 
        <StudyList
          lastStudyElementRef={lastStudyElementRef}
          studyList={studyList}
        ></StudyList>
      )} 
    </>
  );
};

export default ShowByDate;