import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EmailMatchDetailedComponent} from './email-match-detailed.component';

describe('EmailMatchDetailedComponent', () => {
  let component: EmailMatchDetailedComponent;
  let fixture: ComponentFixture<EmailMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmailMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
