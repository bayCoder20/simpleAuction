import { TestBed } from '@angular/core/testing';

import { AuctionListService } from './auction-list.service';

describe('AuctionListService', () => {
  let service: AuctionListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuctionListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
