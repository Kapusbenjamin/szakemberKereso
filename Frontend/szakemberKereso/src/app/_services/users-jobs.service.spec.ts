import { TestBed } from '@angular/core/testing';

import { UsersJobsService } from './users-jobs.service';

describe('UsersJobsService', () => {
  let service: UsersJobsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsersJobsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
