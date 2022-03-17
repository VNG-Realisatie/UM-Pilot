import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SollicitatiewijzeMatchComponent} from './sollicitatiewijze-match.component';

describe('SollicitatiewijzeMatchComponent', () => {
  let component: SollicitatiewijzeMatchComponent;
  let fixture: ComponentFixture<SollicitatiewijzeMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SollicitatiewijzeMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SollicitatiewijzeMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
