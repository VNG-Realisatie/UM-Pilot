import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerktijdenMatchComponent} from './werktijden-match.component';

describe('WerktijdenMatchComponent', () => {
  let component: WerktijdenMatchComponent;
  let fixture: ComponentFixture<WerktijdenMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerktijdenMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerktijdenMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
