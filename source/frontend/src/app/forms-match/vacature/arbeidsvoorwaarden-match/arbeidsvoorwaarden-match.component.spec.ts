import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ArbeidsvoorwaardenMatchComponent} from './arbeidsvoorwaarden-match.component';

describe('ArbeidsvoorwaardenMatchComponent', () => {
  let component: ArbeidsvoorwaardenMatchComponent;
  let fixture: ComponentFixture<ArbeidsvoorwaardenMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbeidsvoorwaardenMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArbeidsvoorwaardenMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
