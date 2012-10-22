package org.kurron.pdf.clown

import org.pdfclown.documents.Document
import org.pdfclown.documents.Page
import org.pdfclown.documents.contents.composition.PrimitiveComposer
import org.pdfclown.documents.contents.fonts.StandardType1Font
import org.pdfclown.documents.interaction.viewer.ViewerPreferences
import org.pdfclown.documents.interchange.metadata.Information
import org.pdfclown.files.File
import org.pdfclown.files.SerializationModeEnum
import spock.lang.Specification
import org.pdfclown.documents.contents.fonts.Font
import java.awt.geom.Point2D
import org.pdfclown.documents.contents.composition.BlockComposer
import java.awt.Dimension
import java.awt.geom.Rectangle2D

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import org.pdfclown.documents.Document;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.contents.composition.AlignmentXEnum;
import org.pdfclown.documents.contents.composition.AlignmentYEnum;
import org.pdfclown.documents.contents.composition.BlockComposer;
import org.pdfclown.documents.contents.composition.PrimitiveComposer;
import org.pdfclown.documents.contents.fonts.Font;
import org.pdfclown.files.File;

/**
 * Learning test for the PDF Clown library.
 */
class ClownTest extends Specification
{
    private static final float Margin = 36

    def "hello"( )
    {
        given: "document"
        File file = new File()
        Document document = file.document

        when: "page is built"
        Page page = new Page( document )
        document.getPages().add( page )
        PrimitiveComposer composer = new PrimitiveComposer( page )
        composer.setFont(                               new StandardType1Font( document,
                                                                               StandardType1Font.FamilyEnum.Courier,
                                                                               true,
                                                                               false ),
                                                        32 )
        composer.showText( "Hello World!",
                           new Point2D.Double( 32, 48 ) )
        composer.flush()
        serialize( file, "hello-world", false, "Hello world", "a simple 'hello world'" )

        then: "pdf file is created"
    }

    def "unicode"( )
    {
        given: "document"
        File file = new File()
        Document document = file.document

        when: "page is built"
        Page page = new Page( document )
        document.getPages().add( page )
        PrimitiveComposer composer = new PrimitiveComposer( page )
        BlockComposer blockComposer = new BlockComposer( composer )
        final URI uri = getClass().getClassLoader().getResource( "GenR102.TTF" ).toURI()
        assert uri
        java.io.File bob = new java.io.File( uri )
        Font font = Font.get( document, bob )
        Dimension breakSize = new Dimension( 0, 10 )
        String[] titles =
            ["ΑΡΘΡΟ 1",
                    "ASARIYA SINTE (1)",
                    "Article 1",
                    "Article premier",
                    "Статья 1",
                    "Artículo 1",
                    "Artikel 1",
                    "Madde 1",
                    "Artikel 1",
                    "Articolo 1",
                    "Artykuł 1",
                    "Bend 1",
                    "Abala kìíní."]
        String[] bodies =
            ["'Ολοι οι άνθρωποι γεννιούνται ελεύθεροι και ίσοι στην αξιοπρέπεια και τα δικαιώματα. Είναι προικισμένοι με λογική και συνείδηση, και οφείλουν να συμπεριφέρονται μεταξύ τους με πνεύμα αδελφοσύνης.",
                    "Aduniya kuna n gu ibuna damayo hɛi nɔ dei-dei nn daama nna n burucinitɛrɛ fɔ, n lasabu nna laakari ya nam nn mɔ huro cɛrɛ kuna nyanze tɛrɛ bɔŋɔɔ.",
                    "All human beings are born free and equal in dignity and rights. They are endowed with reason and conscience and should act towards one another in a spirit of brotherhood.",
                    "Tous les êtres humains naissent libres et égaux en dignité et en droits. Ils sont doués de raison et de conscience et doivent agir les uns envers les autres dans un esprit de fraternité.",
                    "Все люди рождаются свободными и равными в своем достоинстве и правах. Они наделены разумом и совестью и должны поступать в отношении друг друга в духе братства.",
                    "Todos los seres humanos nacen libres e iguales en dignidad y derechos y, dotados como están de razón y conciencia, deben comportarse fraternalmente los unos con los otros.",
                    "Alle Menschen sind frei und gleich an Würde und Rechten geboren. Sie sind mit Vernunft und Gewissen begabt und sollen einander im Geist der Brüderlichkeit begegnen.",
                    "Bütün insanlar hür, haysiyet ve haklar bakımından eşit doğarlar. Akıl ve vicdana sahiptirler ve birbirlerine karşı kardeşlik zihniyeti ile hareket etmelidirler.",
                    "Alla människor är födda fria och lika i värde och rättigheter. De har utrustats med förnuft och samvete och bör handla gentemot varandra i en anda av gemenskap.",
                    "Tutti gli esseri umani nascono liberi ed eguali in dignità e diritti. Essi sono dotati di ragione e di coscienza e devono agire gli uni verso gli altri in spirito di fratellanza.",
                    "Wszyscy ludzie rodzą się wolni i równi pod względem swej godności i swych praw. Są oni obdarzeni rozumem i sumieniem i powinni postępować wobec innych w duchu braterstwa.",
                    "Hemû mirov azad û di weqar û mafan de wekhev tên dinyayê. Ew xwedî hiş û şuûr in û divê li hember hev bi zihniyeteke bratiyê bilivin.",
                    "Gbogbo ènìyàn ni a bí ní òmìnira; iyì àti è̟tó̟ kò̟ò̟kan sì dó̟gba. Wó̟n ní è̟bùn ti làákàyè àti ti è̟rí-o̟kàn, ó sì ye̟ kí wo̟n ó máa hùwà sí ara wo̟n gé̟gé̟ bí o̟mo̟ ìyá."]

        String[] sources =
            ["http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=grk",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=den",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=eng",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=frn",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=rus",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=spn",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=ger",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=trk",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=swd",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=itn",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=pql",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=kdb1",
                    "http://www.ohchr.org/EN/UDHR/Pages/Language.aspx?LangID=yor"]

        final ted = new Rectangle2D.Double( Margin,
                                            Margin,
                                            page.getSize().getWidth() - Margin * 2,
                                            page.getSize().getHeight() - Margin * 2 )

        blockComposer.begin( ted, AlignmentXEnum.Justify, AlignmentYEnum.Top )

        int length = titles.length
        for( int index = 0; index < length;  index++ )
        {
            composer.setFont( font, 12 );
            blockComposer.showText( titles[index] );
            blockComposer.showBreak();

            composer.setFont( font, 11 );
            blockComposer.showText( bodies[index] );
            blockComposer.showBreak( AlignmentXEnum.Right );

            composer.setFont( font, 8 );
            blockComposer.showText( "[Source: " + sources[index] + "]" );
            blockComposer.showBreak( breakSize, AlignmentXEnum.Justify );
        }

        blockComposer.end()
        composer.flush()

        serialize( file, "unicode", false, "Unicode", "using Unicode fonts" )

        then: "pdf file is created"
    }

    def "tl_unicode"( )
    {
        given: "document"
        File file = new File()
        Document document = file.document

        when: "page is built"
        Page page = new Page( document )
        document.getPages().add( page )
        PrimitiveComposer composer = new PrimitiveComposer( page )
        BlockComposer blockComposer = new BlockComposer( composer )
        final URI uri = getClass().getClassLoader().getResource( "HeiT.ttf" ).toURI()
        assert uri
        java.io.File bob = new java.io.File( uri )
        Font font = Font.get( document, bob )
        println 'println name = ' + font.name
        Dimension breakSize = new Dimension( 0, 10 )
        String[] titles =
            ["我的朋友問我給了他這個文本"]
        String[] bodies =
            ["我的朋友問我給了他這個文本"]

        String[] sources =
            ["我的朋友問我給了他這個文本"]

        final ted = new Rectangle2D.Double( Margin,
                                            Margin,
                                            page.getSize().getWidth() - Margin * 2,
                                            page.getSize().getHeight() - Margin * 2 )

        blockComposer.begin( ted, AlignmentXEnum.Justify, AlignmentYEnum.Top )

        int length = titles.length
        for( int index = 0; index < length;  index++ )
        {
            composer.setFont( font, 12 );
            blockComposer.showText( titles[index] );
            blockComposer.showBreak();

            composer.setFont( font, 11 );
            blockComposer.showText( bodies[index] );
            blockComposer.showBreak( AlignmentXEnum.Right );

            composer.setFont( font, 8 );
            blockComposer.showText( "[Source: " + sources[index] + "]" );
            blockComposer.showBreak( breakSize, AlignmentXEnum.Justify );
        }

        blockComposer.end()
        composer.flush()

        serialize( file, "tl-chinese-traditional", false, "Unicode", "using Unicode fonts" )

        then: "pdf file is created"
    }

    protected void serialize( File file,
                              String fileName,
                              boolean chooseMode,
                              String title,
                              String subject )
    {
        applyDocumentSettings( file.getDocument(), title, subject );

        System.out.println();
        SerializationModeEnum serializationMode = SerializationModeEnum.Incremental;
        if( chooseMode )
        {
            Scanner bob = new Scanner( System.in );
            System.out.println( "[0] Standard serialization" );
            System.out.println( "[1] Incremental update" );
            System.out.print( "Please select a serialization mode: " );
            serializationMode = SerializationModeEnum.values()[Integer.parseInt( bob.nextLine() )];
        }

        String outputPath = '.'
        java.io.File outputFile = new java.io.File( outputPath + java.io.File.separator + fileName + "." + serializationMode + ".pdf" );

        // Save the file!
        /*
          NOTE: You can also save to a generic target stream (see save() method overloads).
        */
        try
        {file.save( outputFile, serializationMode );}
        catch( Exception e )
        {
            System.out.println( "File writing failed: " + e.getMessage() );
            e.printStackTrace();
        }

        System.out.println( "\nOutput: " + outputFile.getPath() );
    }

    private void applyDocumentSettings( Document document,
                                        String title,
                                        String subject )
    {
        if( title == null )
            return;

        // Viewer preferences.
        ViewerPreferences view = new ViewerPreferences( document ); // Instantiates viewer preferences inside the document context.
        document.setViewerPreferences( view ); // Assigns the viewer preferences object to the viewer preferences function.
        view.setDisplayDocTitle( true );

        // Document metadata.
        Information info = new Information( document );
        document.setInformation( info );
        info.setAuthor( "Stefano Chizzolini" );
        info.setCreationDate( new Date() );
        info.setCreator( getClass().getName() );
        info.setTitle( "PDF Clown - " + title + " sample" );
        info.setSubject( "Sample about " + subject + " using PDF Clown" );
    }
}
