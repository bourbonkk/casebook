import React from 'react';

const MENUS = [
  {
    to: '/',
    icon: <i className="fas fa-home-alt" />,
    name: '스페이스',
    admin: false,
    pc: true,
    theme: 'black',
    login: true,
    project: false,
  },
  {
    to: '',
    icon: <i className="fas fa-home-alt" />,
    name: '대시보드',
    admin: false,
    pc: true,
    theme: 'black',
    login: true,
    project: true,
  },
  {
    to: '/testcases',
    icon: <i className="fa-solid fa-vial-virus" />,
    name: '테스트케이스',
    admin: false,
    pc: true,
    theme: 'black',
    login: true,
    project: true,
  },
  {
    to: '/testruns',
    icon: <i className="fa-solid fa-circle-play" />,
    name: '테스트 런',
    admin: false,
    pc: true,
    login: true,
    project: true,
  },
  {
    to: '/bugs',
    icon: <i className="fa-solid fa-viruses" />,
    name: '버그',
    admin: false,
    pc: true,
    login: true,
    project: true,
  },
  {
    to: '/reports',
    icon: <i className="fa-regular fa-newspaper" />,
    name: '리포트',
    admin: false,
    pc: true,
    login: true,
    project: true,
  },
  {
    to: '/config',
    icon: <i className="fa-regular fa-newspaper" />,
    name: '설정',
    admin: false,
    pc: true,
    login: true,
    project: true,
  },
];

const DUMMY = {};

export { MENUS, DUMMY };