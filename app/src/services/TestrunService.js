import * as request from '@/utils/request';

const TestrunService = {};

TestrunService.selectProjectTestrunList = (spaceCode, projectId, successHandler, failHandler, loading = true) => {
  return request.get(
    `/api/${spaceCode}/projects/${projectId}/testruns`,
    null,
    res => {
      successHandler(res);
    },
    failHandler,
    null,
    null,
    loading,
  );
};

TestrunService.createProjectTestrunInfo = (spaceCode, projectId, testrun, successHandler, failHandler) => {
  return request.post(
    `/api/${spaceCode}/projects/${projectId}/testruns`,
    testrun,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

export default TestrunService;