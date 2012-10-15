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

import java.awt.geom.Point2D

/**
 * Learning test for the PDF Clown library.
 */
class ClownTest extends Specification
{
    def "hello"( )
    {
        given: "no added text"
        File file = new File()
        Document document = file.document

        when: "build is called"
        Page page = new Page( document )
        document.getPages().add( page )
        PrimitiveComposer composer = new PrimitiveComposer( page )
        composer.setFont( new StandardType1Font( document,
                                                 StandardType1Font.FamilyEnum.Courier,
                                                 true,
                                                 false ),
                                                 32 )
        composer.showText( "Hello World!",
                           new Point2D.Double( 32, 48 ) )
        composer.flush()
        serialize( file, false, "Hello world", "a simple 'hello world'" )

        then: "pdf file is created"
    }

    protected void serialize( File file,
                              boolean chooseMode,
                              String title,
                              String subject )
    {
        serialize( file, getClass().getSimpleName(), chooseMode, title, subject )
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
