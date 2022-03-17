import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkgeverMatchDetailedComponent} from './werkgever-match-detailed.component';

describe('WerkgeverMatchDetailedComponent', () => {
  let component: WerkgeverMatchDetailedComponent;
  let fixture: ComponentFixture<WerkgeverMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkgeverMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkgeverMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
