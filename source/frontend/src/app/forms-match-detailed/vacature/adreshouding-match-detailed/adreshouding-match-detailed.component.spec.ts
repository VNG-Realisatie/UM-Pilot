import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdreshoudingMatchDetailedComponent} from './adreshouding-match-detailed.component';

describe('AdreshoudingMatchDetailedComponent', () => {
  let component: AdreshoudingMatchDetailedComponent;
  let fixture: ComponentFixture<AdreshoudingMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdreshoudingMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdreshoudingMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
