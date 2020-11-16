package vista.web;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.github.alex00fer.ehttpj.EHttpPage;
import io.github.alex00fer.ehttpj.html.EHtmlContainer;
import io.github.alex00fer.ehttpj.html.EHtmlHyperlink;
import io.github.alex00fer.ehttpj.html.EHtmlImage;
import io.github.alex00fer.ehttpj.html.EHtmlList;
import io.github.alex00fer.ehttpj.html.EHtmlNewLine;
import io.github.alex00fer.ehttpj.html.EHtmlParagraph;
import io.github.alex00fer.ehttpj.html.EHtmlRoot;
import io.github.alex00fer.ehttpj.html.EHtmlRoot;
import io.github.alex00fer.ehttpj.html.internal.EHtml;
import io.github.alex00fer.ehttpj.html.internal.EHtmlElementRaw;
import io.github.alex00fer.ehttpj.internal.EHttpExchange;

public class PaginaTemplate extends EHttpPage {
	
	List<EHtmlElementRaw> sections = new LinkedList<EHtmlElementRaw>();
	List<RuntimeSection> runtimeSections = new LinkedList<RuntimeSection>();
	
	public PaginaTemplate(EHtmlElementRaw... sections) {
		this.sections.addAll(Arrays.asList(sections));
	}
	
	public void addRuntimeSection(RuntimeSection... sections) {
		this.runtimeSections.addAll(Arrays.asList(sections));
	}

	@Override
	public EHtml content(EHttpExchange $) {
		EHtmlRoot doc = new EHtmlRoot("ADAT Aerolineas");
		doc.addCSS("static/style.css");
		doc.addCSS("https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
		EHtmlContainer content = new EHtmlContainer();
		content.addStyle("width", "1000px");
		content.addStyle("margin", "0 auto");
		doc.add(content);
		
		// header
		content.add(new EHtmlParagraph("<b>Version 1.0</b>"));
		EHtmlContainer header = new EHtmlContainer();
		header.addStyle("text-align", "center"); // center
		header.add(new EHtmlImage("static/header.png")); // image
		header.add(new ContenedorMenuPrincipal());
		content.add(header);
		// content
		if (sections != null)
			for (EHtmlElementRaw section : sections) {
				section.addStyle("margin-top", "5px");
				section.addStyle("background-color", "#333");
				section.addStyle("color", "white");
				content.add(section);
			}
		if (runtimeSections != null)
			for (RuntimeSection runSection : runtimeSections) {
				EHtmlElementRaw section = runSection.generate($);
				section.addStyle("margin-top", "5px");
				section.addStyle("background-color", "#333");
				section.addStyle("color", "white");
				content.add(section);
			}
		
		return doc;
	}
	
	

}
