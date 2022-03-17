import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdresBuitenlandMatchDetailedComponent} from './adres-buitenland-match-detailed.component';

describe('AdresBuitenlandMatchDetailedComponent', () => {
  let component: AdresBuitenlandMatchDetailedComponent;
  let fixture: ComponentFixture<AdresBuitenlandMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdresBuitenlandMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdresBuitenlandMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
