import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AntwoordnummeradresMatchDetailedComponent} from './antwoordnummeradres-match-detailed.component';

describe('AntwoordnummeradresMatchDetailedComponent', () => {
  let component: AntwoordnummeradresMatchDetailedComponent;
  let fixture: ComponentFixture<AntwoordnummeradresMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AntwoordnummeradresMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AntwoordnummeradresMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
