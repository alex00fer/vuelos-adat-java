package vista.web;

import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;

public interface RuntimeSection {
	EHtmlElementRaw generate(EHttpExchange $);
}
