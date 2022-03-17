import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ArbeidsvoorwaardenMatchDetailedComponent} from './arbeidsvoorwaarden-match-detailed.component';

describe('ArbeidsvoorwaardenMatchDetailedComponent', () => {
  let component: ArbeidsvoorwaardenMatchDetailedComponent;
  let fixture: ComponentFixture<ArbeidsvoorwaardenMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbeidsvoorwaardenMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArbeidsvoorwaardenMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
