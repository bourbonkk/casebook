import React, { useEffect, useState } from 'react';
import { Button, Card, EmptyContent, Page, PageContent, PageTitle } from '@/components';
import { useTranslation } from 'react-i18next';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router';
import ProjectService from '@/services/ProjectService';
import './ProjectListPage.scss';
import { MENUS } from '@/constants/menu';
import SpaceService from '@/services/SpaceService';

function ProjectListPage() {
  const { t } = useTranslation();
  const { spaceCode } = useParams();
  const [space, setSpace] = useState(null);
  const navigate = useNavigate();
  const [projects, setProjects] = useState([]);

  const getSpaceInfo = () => {
    SpaceService.selectSpaceInfo(spaceCode, info => {
      setSpace(info);
    });
  };

  useEffect(() => {
    getSpaceInfo();
    ProjectService.selectMyProjectList(spaceCode, list => {
      setProjects(list);
    });
  }, [spaceCode]);

  return (
    <Page className="project-list-page-wrapper" list>
      <PageTitle
        collapse
        breadcrumbs={[
          {
            to: '/',
            text: t('HOME'),
          },
          {
            to: '/',
            text: t('스페이스 목록'),
          },
          {
            to: `/spaces/${spaceCode}/info`,
            text: space?.name,
          },
          {
            to: `/spaces/${spaceCode}/projects`,
            text: t('프로젝트 목록'),
          },
        ]}
        links={[
          {
            to: `/spaces/${spaceCode}/projects/new`,
            text: t('새 프로젝트'),
            color: 'primary',
            icon: <i className="fa-solid fa-plus" />,
          },
        ]}
        onListClick={() => {
          navigate('/spaces');
        }}
      >
        {t('프로젝트')}
      </PageTitle>
      <PageContent className="content">
        {projects?.length <= 0 && (
          <EmptyContent border fill>
            <div>
              <div>{t('아직 생성된 프로젝트가 없습니다.')}</div>
              <div>
                <Button
                  outline
                  color="primary"
                  onClick={() => {
                    navigate(`/spaces/${spaceCode}/projects/new`);
                  }}
                >
                  <i className="fa-solid fa-plus" /> {t('프로젝트 생성')}
                </Button>
              </div>
            </div>
          </EmptyContent>
        )}
        {projects?.length > 0 && (
          <ul className="project-list">
            {projects?.map(project => {
              return (
                <li key={project.id}>
                  <Card className="project-card" border>
                    <div className="project-info">
                      <div
                        className="name"
                        onClick={() => {
                          navigate(`/spaces/${spaceCode}/projects/${project.id}`);
                        }}
                      >
                        {project.name}
                      </div>
                      <div className="summary">
                        <div>
                          <div className="description">{project.description}</div>
                          <div className="count">
                            <div className="bug-info">
                              <div
                                className="bug-count"
                                onClick={() => {
                                  navigate(`/spaces/${spaceCode}/projects/${project.id}/bugs`);
                                }}
                              >
                                <div className="icon">
                                  <i className="fa-solid fa-virus" />
                                </div>
                                <div className="number">{project.bugCount}</div>
                                <div className="label">BUGS</div>
                              </div>
                            </div>
                            <div>
                              <div
                                className="tc-count"
                                onClick={() => {
                                  navigate(`/spaces/${spaceCode}/projects/${project.id}/testcases`);
                                }}
                              >
                                <div className="icon">
                                  <i className="fa-solid fa-vial-virus" />
                                </div>
                                <div className="number">{project.testcaseCount}</div>
                                <div className="label">TESTCASES</div>
                              </div>
                            </div>
                            <div>
                              <div
                                className="tr-count"
                                onClick={() => {
                                  navigate(`/spaces/${spaceCode}/projects/${project.id}/testruns`);
                                }}
                              >
                                <div className="icon">
                                  <i className="fa-solid fa-scale-balanced" />
                                </div>
                                <div className="number">{project.testrunCount}</div>
                                <div className="label">TESTRUNS</div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div className="side-menu">
                        <ul>
                          {MENUS.map((menu, inx) => {
                            return (
                              <li
                                key={inx}
                                className={menu.key}
                                onClick={() => {
                                  navigate(`/spaces/${spaceCode}/projects/${project.id}${menu.to}`);
                                }}
                              >
                                <div>
                                  <div className="tooltip">
                                    <span>{t(`메뉴.${menu.name}`)}</span>
                                    <div className="arrow" />
                                  </div>
                                  {menu.icon}
                                </div>
                              </li>
                            );
                          })}
                        </ul>
                      </div>
                    </div>
                  </Card>
                </li>
              );
            })}
          </ul>
        )}
      </PageContent>
    </Page>
  );
}

export default ProjectListPage;
