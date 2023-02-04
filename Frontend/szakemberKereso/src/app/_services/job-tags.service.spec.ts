import { TestBed } from '@angular/core/testing';

import { JobTagsService } from './job-tags.service';

describe('JobTagsService', () => {
  let service: JobTagsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobTagsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
