import { TestBed } from '@angular/core/testing';

import { AdsCountiesService } from './ads-counties.service';

describe('AdsCountiesService', () => {
  let service: AdsCountiesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdsCountiesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
