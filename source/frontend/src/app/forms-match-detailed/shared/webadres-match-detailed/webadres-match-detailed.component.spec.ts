import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WebadresMatchDetailedComponent} from './webadres-match-detailed.component';

describe('WebadresMatchDetailedComponent', () => {
  let component: WebadresMatchDetailedComponent;
  let fixture: ComponentFixture<WebadresMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WebadresMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WebadresMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
